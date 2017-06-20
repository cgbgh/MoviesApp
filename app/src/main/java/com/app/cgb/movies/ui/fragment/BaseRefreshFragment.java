package com.app.cgb.moviepreview.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.utils.NetWorkUtil;
import com.app.cgb.rlrecyclerview.RLRecyclerView;

import butterknife.BindView;


public abstract class BaseRefreshFragment<T> extends BaseRequstFragment<T> implements RLRecyclerView.OnRefreshListener {

    private static final String TAG = BaseRefreshFragment.class.getSimpleName();
    @BindView(R.id.rlrv_list)
    RLRecyclerView rvList;

    @Override
    protected int inflateView() {
        return R.layout.fragment_refresh_rvlist;
    }

    @Override
    protected void initView() {
        setupList();
    }

    public RLRecyclerView getRvList() {
        return rvList;
    }

    private void setupList() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        rvList.setLayoutManager(layoutManager);
        rvList.setOnRefreshListener(this);
        rvList.setCanLoadMore(false);
        rvList.setAdapter(getAdapter());
    }

    protected abstract RecyclerView.Adapter getAdapter();

    protected RecyclerView.LayoutManager getLayoutManager(){
        return new LinearLayoutManager(mContext);
    }

    protected void reset() {
    }

    @Override
    public void scrollToTop() {
        rvList.smoothScrollToPosition(0);
    }

    @Override
    public boolean onRefresh() {
        if (NetWorkUtil.isNetWorkAvailable(mContext)) {
            reset();
            request();
            return true;
        }
        return false;
    }

    @Override
    public void showLoading() {
        rvList.setRefreshing(true);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        if (isSuccess) {
            rvList.refreshComplete();
        } else {
            rvList.refreshError();
        }
    }
}
