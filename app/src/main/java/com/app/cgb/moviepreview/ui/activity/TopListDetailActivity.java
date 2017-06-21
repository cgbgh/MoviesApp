package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.entity.TopListDetails;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.TopListDetailAdapter;
import com.app.cgb.moviepreview.utils.ToastUtils;

import butterknife.BindView;

public class TopListDetailActivity extends BaseRequestActivity<TopListDetails> implements BaseSingleTypeAdapter.ItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private int mPageIndex;
    private int mPageCount;
    private TopListDetailAdapter mAdapter;
    private boolean isLoadMore;
    private int mTopListId;
    private int mType;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        setupToolbar(toolbar, "排行榜详情");
        setupList();
    }


    private void setupList() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new TopListDetailAdapter(this);
        rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mTopListId = getIntent().getIntExtra(Constants.MT_TOPLIST_DETAIL_ID, -1);
        mType = getIntent().getIntExtra(Constants.MT_TOPLIST_TYPE, -1);
        mPageIndex = 1;
    }

    public void setTopListDetailData(TopListDetails topListDetailData) {
        if (topListDetailData.getTopList() == null) {
            mAdapter.setNoData();
            return;
        }
        mPageCount = topListDetailData.getPageCount();
        if (mPageIndex >= mPageCount) {
            mAdapter.setNoMoreData();
        }
        if (!isLoadMore) {
            TopListDetails.TopListBean topList = topListDetailData.getTopList();
            if (getSupportActionBar() != null){
                getSupportActionBar().setTitle(topList.getName());
            }
            mAdapter.setType(mType);
            if (mType == 0 || mType == 1) {
                mAdapter.setData(topListDetailData.getMovies());
            } else if (mType == 2) {
                mAdapter.setData(topListDetailData.getPersons());
            }
            mAdapter.setHeadView(topList.getName(), topList.getSummary());
            return;
        } else {
            if (mType == 0 || mType == 1) {
                mAdapter.addData(topListDetailData.getMovies());
            } else if (mType == 2) {
                mAdapter.addData(topListDetailData.getPersons());
            }
        }
        mPageIndex++;
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadTopListDetail(mTopListId,mPageIndex);
    }

    @Override
    protected void onResponse(TopListDetails response) {
        setTopListDetailData(response);
    }

    @Override
    public void showLoading() {
        if (!isLoadMore) {
            pbLoading.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.GONE);
        }else {
            mAdapter.startLoading();
        }
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        if (isLoadMore) {
            isLoadMore = false;
            mAdapter.stopLoading();
        }else {
            pbLoading.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(int position) {
        if (mType == 0 || mType == 1) {
            TopListDetails.MoviesBean item = (TopListDetails.MoviesBean) mAdapter.getItem(position + 1);
            startDetailActivity(MTMovieDetailActivity.class,item.getId(), Constants.MOVIE_ID);
        } else if (mType == 2) {
            TopListDetails.PersonsBean item = (TopListDetails.PersonsBean) mAdapter.getItem(position + 1);
            startDetailActivity(PersonDetailActivity.class,item.getId(), Constants.PERSON_ID);
        }
    }

    private void startDetailActivity(Class activity,int id,String key) {
        Intent intent = new Intent(this, activity);
        intent.putExtra(key, id);
        this.startActivity(intent);
    }

    @Override
    public void onHeadClick() {
    }

    @Override
    public void onFootClick() {
        if (isLoadMore) {
            return;
        }
        if (mPageIndex < mPageCount) {
            isLoadMore = true;
            request();
        } else {
            ToastUtils.showShortToastSafe(this, "已经到底了");
        }
    }

}
