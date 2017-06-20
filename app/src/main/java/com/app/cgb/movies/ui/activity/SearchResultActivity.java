package com.app.cgb.moviepreview.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.cgb.moviepreview.basic.BaseActivity;
import com.app.cgb.moviepreview.basic.BaseAdapter;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.SearchResult;
import com.app.cgb.moviepreview.presenter.SearchResultPresenterImpl;
import com.app.cgb.moviepreview.ui.adapter.MovieSearchResultAdapter;
import com.app.cgb.moviepreview.ui.adapter.SearchResultAdapter;
import com.app.cgb.moviepreview.Iview.ISearchResultView;
import com.app.cgb.moviepreview.utils.ToastUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class SearchResultActivity extends BaseActivity implements ISearchResultView, View.OnClickListener, View.OnKeyListener {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_vp_layout)
    LinearLayout tabVpLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private SearchResultPresenterImpl mPresenter;
    private ProgressDialog progressDialog;
    private boolean isRefreshing;
    private MovieSearchResultAdapter itemAdapter0;
    private MovieSearchResultAdapter itemAdapter1;
    private boolean moivePageLoading;
    private boolean personPageLoading;
    private String keyword;
    private int moviePageIndex;
    private int personPageIndex;
    private int moviesCount;
    private int personsCount;
    private boolean noMorePerson;
    private boolean noMoreMovie;
    private String mCurrentKeyword;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        setupNoTitleToolbar(toolbar);
        setupListener();
        showKeyboard();
        tabLayout.setupWithViewPager(vpContent);
        tabVpLayout.setVisibility(View.INVISIBLE);
    }

    private void showKeyboard() {
        etSearch.setFocusable(true);
        etSearch.setFocusableInTouchMode(true);
        etSearch.requestFocus();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etSearch,0);
            }
        },500);
    }


    private void setupListener() {
        ivSearch.setOnClickListener(this);
        etSearch.setOnKeyListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                search();
                break;
        }
    }

    private void search() {
        if (isRefreshing){
            return;
        }
        keyword = etSearch.getText().toString().trim();
        if (keyword.equals(mCurrentKeyword)){
            return;
        }
        mCurrentKeyword = keyword;
        if (!keyword.isEmpty()){
            reset();
            loadData(1);
        }else {
            showToast("输入不能为空，请重新输入");
        }
    }

    private void reset() {
        isRefreshing = true;
        moviePageIndex = 1;
        personPageIndex = 1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeybord();
    }

    private void hideKeybord() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(etSearch.getApplicationWindowToken(), 0);
        }
    }

    private void loadData(int pageIndex) {

        hideKeybord();

        if (mPresenter == null) {
            mPresenter = new SearchResultPresenterImpl(this);
        }
        mPresenter.loadSearchResult(3, keyword, pageIndex);
    }

    @Override
    public void setSearchResultData(SearchResult searchResultData) {
        tabVpLayout.setVisibility(View.VISIBLE);
        if (isRefreshing) {
            moviesCount = searchResultData.getMoviesCount();
            personsCount = searchResultData.getPersonsCount();
            setupViewPager(searchResultData);
        } else {
            addDatas(searchResultData);
        }
//        pageIndex++;
        if ((moviePageIndex - 1) * 20 >= moviesCount) {
            if (itemAdapter0 != null) {
                itemAdapter0.setNoMoreData(true);
                noMoreMovie = true;
            }
        }
        if ((personPageIndex - 1) * 20 >= personsCount) {
            if (itemAdapter1 != null) {
                itemAdapter1.setNoMoreData(true);
                noMorePerson = true;
            }
        }
    }

    private void addDatas(SearchResult searchResultData) {
        if (moivePageLoading) {
            if (itemAdapter0 != null) {
                itemAdapter0.addData(searchResultData.getMovies());
                itemAdapter0.stopLoading();
                moviePageIndex++;
            }
            moivePageLoading = false;
        }
        if (personPageLoading) {
            if (itemAdapter1 != null) {
                itemAdapter1.addData(searchResultData.getPersons());
                itemAdapter1.stopLoading();
                personPageIndex++;
            }
            personPageLoading = false;
        }
    }

    private void setupViewPager(SearchResult searchResult) {
        SearchResultAdapter mAdapter = new SearchResultAdapter(this, searchResult);
        vpContent.setAdapter(mAdapter);
        if (searchResult.getPersonsCount() > 0) {
            vpContent.setCurrentItem(1);
        }
        setupItemClickListener(mAdapter);
        moviePageIndex++;
        personPageIndex++;
    }

    private void setupItemClickListener(SearchResultAdapter adapter) {
        final List<BaseAdapter> itemAdapters = adapter.getItemAdapters();
        if (itemAdapters != null && itemAdapters.size() == 2) {

            itemAdapter0 = (MovieSearchResultAdapter) itemAdapters.get(0);
            itemAdapter1 = (MovieSearchResultAdapter) itemAdapters.get(1);
            itemAdapter0.setOnItemClickListener(new BaseSingleTypeAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    SearchResult.MoviesBean item = (SearchResult.MoviesBean) itemAdapter0.getItem(position);
                    int id = item.getId();
                    startActivityWithId(MTMovieDetailActivity.class, Constants.MOVIE_ID, id);
                }

                @Override
                public void onHeadClick() {

                }

                @Override
                public void onFootClick() {
                    ToastUtils.showShortToastSafe(SearchResultActivity.this, "loading");
                    if (moivePageLoading) {
                        return;
                    }
                    if (!noMoreMovie) {
                        moivePageLoading = true;
                        itemAdapter0.startLoading();
                        loadData(moviePageIndex);
                    } else {
                        showToast("已经到底了");
                    }
                }
            });

            itemAdapter1.setOnItemClickListener(new BaseSingleTypeAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    SearchResult.PersonsBean item = (SearchResult.PersonsBean) itemAdapter1.getItem(position);
                    int id = item.getId();
                    startActivityWithId(PersonDetailActivity.class, Constants.PERSON_ID, id);

                }

                @Override
                public void onHeadClick() {

                }

                @Override
                public void onFootClick() {
                    ToastUtils.showShortToastSafe(SearchResultActivity.this, "loading");
                    if (personPageLoading) {
                        return;
                    }
                    if (!noMorePerson) {
                        personPageLoading = true;
                        itemAdapter1.startLoading();
                        loadData(personPageIndex);
                    } else {
                        showToast("已经到底了");
                    }
                }
            });
        }

    }

    private void startActivityWithId(Class activity, String key, int id) {
        Intent intent = new Intent(SearchResultActivity.this, activity);
        intent.putExtra(key, id);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        if (isRefreshing) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            isRefreshing = false;
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShortToastSafe(this, msg);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            search();
            return true;
        }
        return false;
    }
}
