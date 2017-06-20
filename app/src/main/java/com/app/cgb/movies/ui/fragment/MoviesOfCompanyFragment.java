package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MoviesOfCompany;
import com.app.cgb.moviepreview.presenter.MovieOfCompanyPresenterImpl;
import com.app.cgb.moviepreview.ui.activity.MTMovieDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.MoviesOfCompanyAdapter;
import com.app.cgb.moviepreview.Iview.IMovieOfCompany;
import com.app.cgb.moviepreview.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class MoviesOfCompanyFragment extends BaseFragment implements IMovieOfCompany, BaseSingleTypeAdapter.ItemClickListener {

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private MovieOfCompanyPresenterImpl mPresenter;
    private int pageIndex;
    private int mType;
    private int mCompanyId;
    private boolean firstLoad;
    private int pageCount;
    private MoviesOfCompanyAdapter mAdapter;
    private boolean isLoading;

    public static MoviesOfCompanyFragment newInstance(int type, int companyId) {
        Bundle args = new Bundle();
        args.putInt(Constants.COMPANY_TYPE,type);
        args.putInt(Constants.COMPANY_ID,companyId);
        MoviesOfCompanyFragment fragment = new MoviesOfCompanyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int inflateView() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new MoviesOfCompanyAdapter(mContext);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new MovieOfCompanyPresenterImpl(this);
        pageIndex = 1;
        firstLoad = true;
        Bundle args = getArguments();
        mType = args.getInt(Constants.COMPANY_TYPE);
        mCompanyId = args.getInt(Constants.COMPANY_ID);
        mPresenter.loadMovieOfCompany(mType,mCompanyId,pageIndex);
        Log.d(">>>>","id"+mCompanyId);
    }

    @Override
    public void scrollToTop() {
        rvList.smoothScrollToPosition(0);
    }

    @Override
    public void setMovieOfCompany(MoviesOfCompany datas) {
        List<MoviesOfCompany.MoviesBean> movies = datas.getMovies();
        if (firstLoad){
            pageCount = datas.getCurrentPageCount();
        }
        if (movies!=null && movies.size()>0){
            mAdapter.addData(movies);
        }
        if (pageIndex>=pageCount){
            mAdapter.setNoMoreData(true);
        }
        pageIndex++;
    }

    @Override
    public void showLoading() {
        if (firstLoad){
            pbLoading.setVisibility(View.VISIBLE);
        }else {
            mAdapter.startLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (firstLoad){
            pbLoading.setVisibility(View.GONE);
            firstLoad = false;
        }else {
            mAdapter.stopLoading();
            isLoading=false;
        }

    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShortToastSafe(mContext,msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter!=null){
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    @Override
    public void onItemClick(int position) {
        MoviesOfCompany.MoviesBean item = mAdapter.getItem(position);
        int id = item.getId();
        Intent intent = new Intent(mContext, MTMovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID,id);
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
        if (pageIndex<=pageCount){
            mPresenter.loadMovieOfCompany(mType,mCompanyId,pageIndex);
            isLoading =true;
        }else {
            ToastUtils.showShortToastSafe(mContext,"已经到底了");
        }
    }
}
