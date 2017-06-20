package com.app.cgb.moviepreview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.Images;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.fragment.PicDetailFragment;
import com.app.cgb.moviepreview.ui.fragment.StaggeredPicsFragment;

import java.util.List;


public class PicActivity extends BaseRequestActivity<Images> {

    private final String STAGGERED_TAG = "staggered";
    private final String DETAIL_TAG = "detail";
    private BaseFragment currentFragment;
    private int personId;
    private int movieId;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_pics_layout;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
        personId = getIntent().getIntExtra(Constants.PERSON_ID, -1);

    }

    private void addFragment(Images imagesData) {
        StaggeredPicsFragment staggeredPicsFragment = StaggeredPicsFragment.newInstance(imagesData);
        PicDetailFragment picDetailFragment = PicDetailFragment.newInstance(imagesData.getImages(), 0);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_content, staggeredPicsFragment, STAGGERED_TAG)
                .add(R.id.fl_content, picDetailFragment, DETAIL_TAG)
                .hide(picDetailFragment)
                .commit();
        currentFragment = staggeredPicsFragment;
    }

    public void switch2PicDetailFragment(List<Integer> positionList, int position) {
        FragmentManager fm = getSupportFragmentManager();
        PicDetailFragment fragment = (PicDetailFragment) fm.findFragmentByTag(DETAIL_TAG);
        if (fragment == null) {
            fragment = PicDetailFragment.newInstance(getResponse().getImages(), position);
            fragment.setData(positionList, position);
            fm.beginTransaction()
                    .add(R.id.fl_content, fragment, DETAIL_TAG)
                    .hide(currentFragment)
                    .commit();
        } else {
            fragment.setData(positionList, position);
            fm.beginTransaction()
                    .hide(currentFragment)
                    .show(fragment)
                    .commit();
        }
        currentFragment = fragment;
    }

    public void switch2StaggeredPicsFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        StaggeredPicsFragment fragment = (StaggeredPicsFragment) fm.findFragmentByTag(STAGGERED_TAG);
        if (fragment == null) {
            fragment = StaggeredPicsFragment.newInstance(getResponse());
            fm.beginTransaction()
                    .add(R.id.fl_content, fragment, STAGGERED_TAG)
                    .hide(currentFragment)
                    .commit();
        } else {
            fragment.scrollToPosition(position);
            fm.beginTransaction()
                    .hide(currentFragment)
                    .show(fragment)
                    .commit();
        }
        currentFragment = fragment;
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        if (movieId != -1) {
            model.loadMovieImages(movieId);
        } else if (personId != -1) {
            model.loadPersonImages(personId);
        }
    }

    @Override
    protected void onResponse(Images response) {
        addFragment(response);
    }
}
