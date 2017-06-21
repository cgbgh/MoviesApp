package com.app.cgb.moviepreview.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.cgb.moviepreview.ui.fragment.MovieComingNewFragment;
import com.app.cgb.moviepreview.ui.fragment.MovieInTheaterFragment;
import com.app.cgb.moviepreview.ui.fragment.MovieReviewFragment;
import com.app.cgb.moviepreview.ui.fragment.NewsFragment;
import com.app.cgb.moviepreview.ui.fragment.TrailerListFragment;

public class MTimePagerAdapter extends FragmentPagerAdapter{

    private static final int PAGE_COUNT = 2;
    public static final int TYPE_MOVIE_LIST = 101;
    public static final int TYPE_NEWS_LIST = 102;
    private static final int NEWS_PAGE_COUNT = 3;
    private final int mType;
    private Fragment mNewsFragment;
    private Fragment mMovieInTheaterFragment;
    private Fragment mMovieComingNewFragment;
    private Fragment mTrailerListFragment;
    private Fragment mMovieReviewFragment;

    public MTimePagerAdapter(FragmentManager fm, int type) {
        super(fm);
        mType = type;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (mType == TYPE_MOVIE_LIST){
                    return mMovieInTheaterFragment == null ?
                            mMovieInTheaterFragment = new MovieInTheaterFragment() :
                            mMovieInTheaterFragment;
                }else if (mType == TYPE_NEWS_LIST){
                        return mNewsFragment == null ?
                                mNewsFragment = new NewsFragment() : mNewsFragment;
                }
            case 1:
                if (mType == TYPE_MOVIE_LIST){
                    return mMovieComingNewFragment == null ?
                            mMovieComingNewFragment = new MovieComingNewFragment() :
                            mMovieComingNewFragment;
                }else if (mType == TYPE_NEWS_LIST){
                    return mTrailerListFragment == null ?
                            mTrailerListFragment = new TrailerListFragment() :
                            mTrailerListFragment;
                }
            case 2:
                if (mType == TYPE_NEWS_LIST){
                    return mMovieReviewFragment == null ?
                            mMovieReviewFragment = new MovieReviewFragment() :
                            mMovieReviewFragment;
                }
        }
        return null;
    }

    @Override
    public int getCount() {
        if (mType == TYPE_MOVIE_LIST){
            return PAGE_COUNT;
        }
        return NEWS_PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                if (mType == TYPE_NEWS_LIST){
                    return "新闻";
                }else if (mType == TYPE_MOVIE_LIST){
                    return "正在热映";
                }
            case 1:
                if (mType == TYPE_NEWS_LIST){
                    return "预告片";
                }else if (mType == TYPE_MOVIE_LIST){
                    return "即将上映";
                }

            case 2:
                if (mType == TYPE_NEWS_LIST){
                    return "影评";
                }
        }
        return super.getPageTitle(position);
    }

}
