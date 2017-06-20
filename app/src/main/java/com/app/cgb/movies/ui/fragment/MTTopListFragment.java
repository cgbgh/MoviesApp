package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MTTopLists;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.activity.TopListDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.utils.NetWorkUtil;
import com.app.cgb.rlrecyclerview.DividerItemDecoration;
import com.app.cgb.rlrecyclerview.RLRecyclerView;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;


import java.util.List;

public class MTTopListFragment extends BaseRefreshFragment<MTTopLists> implements
        RLRecyclerView.OnLoadListener {

    private static final String TAG = MTTopListFragment.class.getSimpleName();
    private int mPageIndex;
    private boolean isLoadMore;
    private int mPageCount;
    private boolean isRefresh;
    private CommonAdapter<MTTopLists.TopListsBean> adapter;
    private CommonAdapter.OnItemInit mOnItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            int type = holder.getItemViewType();
            if (type != CommonAdapter.ITEM_HEAD) {
                setupItem(holder, position, data);
            }
        }
    };

    private void setupItem(ViewHolder holder, int position, List data) {
        final MTTopLists.TopListsBean item = (MTTopLists.TopListsBean) data.get(position);
        holder.setText(R.id.tv_title, item.getTopListNameCn())
                .setText(R.id.tv_desc, item.getSummary())
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick(item);
                    }
                });
    }

    @Override
    protected void initView() {
//        setUserVisibleHint(true);
        super.initView();
        getRvList().setCanLoadMore(true);
        getRvList().addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        getRvList().setOnLoadListener(this);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        adapter = new CommonAdapter<>();
        adapter.setHead(R.layout.toplist_head)
                .setLayoutId(R.layout.mt_toplist_item)
                .setOnItemInit(mOnItemInit);
        return adapter;
    }

    @Override
    protected void initData() {
        reset();
        rvList.setRefreshing(true);
        super.initData();
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadMTTopLists(mPageIndex);
    }

    @Override
    protected void reset() {
        isRefresh = true;
        isLoadMore = false;
        mPageIndex = 1;
    }

    @Override
    public void showLoading() {
        if (isRefresh){
            super.showLoading();
        }
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        if (isRefresh){
            isRefresh = false;
            super.hideLoading(isSuccess);
        }
        if (isLoadMore) {
            if (isSuccess){
                rvList.loadComplete();
                isLoadMore = false;
            }else {
                rvList.loadError();
            }
        }
    }

    @Override
    public void onResponse(MTTopLists mtTopLists) {
        if (isRefresh) {
            mPageCount = mtTopLists.getPageCount();
            adapter.setData(mtTopLists.getTopLists());
        } else if (isLoadMore) {
            adapter.addData(mtTopLists.getTopLists());
        }
        if (mPageIndex >= mPageCount) {
            rvList.setNoMoreData(true);
            return;
        }
        mPageIndex++;
    }

    public void onItemClick(MTTopLists.TopListsBean item) {
        int id = item.getId();
        int type = item.getType();
        Intent intent = new Intent(mContext, TopListDetailActivity.class);
        intent.putExtra(Constants.MT_TOPLIST_DETAIL_ID, id);
        intent.putExtra(Constants.MT_TOPLIST_TYPE, type);
        mContext.startActivity(intent);
    }

    @Override
    public boolean onLoad() {
        if (NetWorkUtil.isNetWorkAvailable(mContext)) {
            isLoadMore = true;
            request();
            return true;
        }
        return false;
    }
}
