package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.VideoList;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.VideoListAdapter;
import com.app.cgb.moviepreview.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by cgb on 2017/3/13.
 */
public class VideoListActivtity extends BaseRequestActivity<VideoList> implements BaseSingleTypeAdapter.ItemClickListener {


    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private int pageIndex = 1;
    private boolean isFirstLoad;
    private int pageCount;
    private VideoListAdapter mAdapter;
    private boolean isLoading;
    private int movieId;
    private VideoList mVideoList;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        setupToolbar();
        setupList();
    }


    private void setupList() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter = new VideoListAdapter(this);
        rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.activity_videolist_toolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
        isFirstLoad = true;
    }

    public void setVideoListData(VideoList videoListData) {
        mVideoList = videoListData;
        List<VideoList.VideoListBean> videoList = videoListData.getVideoList();
        if (videoList !=null && videoList.size()>0){
            if (isFirstLoad){
                pageCount = videoListData.getTotalPageCount();
            }else {
                mAdapter.stopLoading();
            }
            mAdapter.addData(videoList);
            pageIndex++;
            if (pageIndex > pageCount){
                mAdapter.setNoMoreData(true);
            }
        }
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadVideoList(pageIndex,movieId);
    }

    @Override
    protected void onResponse(VideoList response) {
        setVideoListData(response);
    }

    @Override
    public void showLoading() {
        if (isFirstLoad){
            pbLoading.setVisibility(View.VISIBLE);
            llRoot.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        if (isFirstLoad){
            pbLoading.setVisibility(View.GONE);
            llRoot.setVisibility(View.VISIBLE);
            isFirstLoad = false;
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, SystemPlayer.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.VIDEOLIST, mVideoList);
        intent.putExtras(bundle);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onHeadClick() {

    }

    @Override
    public void onFootClick() {
        if (isLoading){
            return;
        }
        if (pageIndex < pageCount){
            isLoading = true;
            mAdapter.startLoading();
            request();
        }else {
            ToastUtils.showShortToastSafe(this,"已经到底了");
        }
    }
}
