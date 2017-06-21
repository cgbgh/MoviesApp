package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieNews;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.NewsAdpater;
import com.app.cgb.moviepreview.utils.ToastUtils;

import butterknife.BindView;

public class MovieNewsActivity extends BaseRequestActivity<MovieNews> implements NewsAdpater.OnItemClickListener {

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private int movieId;
    private int mPageIndex;
    private boolean mNoMoreData;
    private NewsAdpater mAdapter;
    private boolean mIsLoading;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        setupToolbar(toolbar,R.string.activity_movie_news_toolbar);
        setupList();
    }

    private void setupList() {
        mAdapter = new NewsAdpater(this, NewsAdpater.FROM_MOVIE);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
        reset();
    }

    private void reset() {
        mPageIndex = 1;
        mIsLoading = false;
        mNoMoreData = false;
        mAdapter.setNoMoreData(false);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        if (!mIsLoading) {
            reset();
        }
        model.loadMovieNews(mPageIndex,movieId);
    }

    @Override
    protected void onResponse(MovieNews response) {
        mAdapter.addData(response.getNewsList());
        int totalCount = response.getTotalCount();
        if (mPageIndex * 10 >= totalCount ||
                response.getNewsList() == null ||
                response.getNewsList().size() < 10) {
            mNoMoreData = true;
            mAdapter.setNoMoreData(true);
        }
        mPageIndex++;
    }

    @Override
    public void showLoading() {
//        pbLoading.setVisibility(View.VISIBLE);
        if (mIsLoading) {
            mAdapter.startLoading();
        }
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        pbLoading.setVisibility(View.GONE);
        mAdapter.stopLoading();
        mIsLoading = false;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_ID, mAdapter.getItem(position).getId());
        intent.putExtra(Constants.NEWS_TYPE,mAdapter.getItem(position).getType());
        startActivity(intent);
    }

    @Override
    public void onFootClick() {
        if (mIsLoading) {
            return;
        }
        if (!mNoMoreData) {
            mIsLoading = true;
            request();
        } else {
            ToastUtils.showShortToastSafe(this, "已经到底了");
        }
    }
}
