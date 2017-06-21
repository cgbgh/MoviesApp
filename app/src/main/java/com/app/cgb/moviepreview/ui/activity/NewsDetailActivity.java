package com.app.cgb.moviepreview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.entity.NewsDetail;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.fragment.NewsContentsFragment;
import com.app.cgb.moviepreview.ui.fragment.NewsPicsFragment;

import butterknife.BindView;

public class NewsDetailActivity extends BaseRequestActivity<NewsDetail> {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    public static final String CONTENT_TAG = NewsContentsFragment.class.getSimpleName();
    public static final String PIC_TAG = NewsPicsFragment.class.getSimpleName();
    private int newsId;
    private Fragment currentFragment;
    private NewsDetail newsDetail;
    private int newsType;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initView() {
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        newsId = getIntent().getIntExtra(Constants.NEWS_ID, -1);
        newsType = getIntent().getIntExtra(Constants.NEWS_TYPE, -1);
        addFragment(newsType);
    }

    private void addFragment(int newsType) {
        BaseFragment fragment;
        String tag = CONTENT_TAG;
        if (newsType != 1){
            fragment = NewsContentsFragment.newInstance(newsType);
        }else {
            fragment = NewsPicsFragment.newInstance(newsType);
            tag = PIC_TAG;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_content, fragment, tag)
                .commit();
        currentFragment = fragment;
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadNewsDetail(newsId);
    }

//    private void addFragment(NewsDetail newsDetail) {
//        NewsContentsFragment newsContentsFragment = NewsContentsFragment.newInstance(newsDetail);
//        NewsPicsFragment newsPicsFragment = NewsPicsFragment.newInstance(newsDetail);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if (newsDetail.getType() == 1) {
//            currentFragment = newsPicsFragment;
//        } else {
//            currentFragment = newsContentsFragment;
//        }
//        transaction.add(R.id.fl_content, newsPicsFragment, PIC_TAG)
//                .add(R.id.fl_content, newsContentsFragment, CONTENT_TAG)
//                .hide(newsPicsFragment)
//                .hide(newsContentsFragment)
//                .show(currentFragment)
//                .commit();
//    }
//
    public void switchFragment(String tag, int currentItem) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = getFragmentByTag(tag);
            if (fragment != currentFragment) {
                manager.beginTransaction()
                        .add(R.id.fl_content, fragment, PIC_TAG)
                        .hide(currentFragment)
                        .commit();
            }
        } else {
            if (fragment != currentFragment) {
                manager.beginTransaction()
                        .hide(currentFragment)
                        .show(fragment)
                        .commit();
            }
        }
        currentFragment = fragment;
        if (currentFragment instanceof NewsPicsFragment) {
            ((NewsPicsFragment) currentFragment).setCurrentPosition(currentItem);
        }
    }

    @JavascriptInterface
    public void showPic(final int position) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switchFragment(NewsDetailActivity.PIC_TAG, position);
            }
        });
    }

    private BaseFragment getFragmentByTag(String tag) {
        if (tag.equals(PIC_TAG)) {
            NewsPicsFragment fragment = NewsPicsFragment.newInstance(newsType);
            fragment.setData(newsDetail);
            return fragment;
        }
        else if (tag.equals(CONTENT_TAG)) {
            NewsContentsFragment fragment = NewsContentsFragment.newInstance(newsType);
            fragment.setData(newsDetail);
            return fragment;
        }
        return null;
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
//        flContent.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        pbLoading.setVisibility(View.GONE);
        flContent.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResponse(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
//        addFragment(newsDetail);
        if (currentFragment instanceof  NewsPicsFragment){
            ((NewsPicsFragment) currentFragment).setData(newsDetail);
        }else if (currentFragment instanceof  NewsContentsFragment){
            ((NewsContentsFragment) currentFragment).setData(newsDetail);
        }
    }

    @Override
    public void onBackPressed() {
        if (newsDetail != null && newsDetail.getType() != 1
                && currentFragment instanceof NewsPicsFragment) {
            switchFragment(CONTENT_TAG, 0);
        } else {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        }
    }
}
