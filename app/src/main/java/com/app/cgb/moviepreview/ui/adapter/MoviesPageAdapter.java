package com.app.cgb.moviepreview.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.cgb.moviepreview.ui.fragment.MoviesOfCompanyFragment;

public class MoviesPageAdapter extends FragmentPagerAdapter{

    private static final int MOVIE_PAGE_COUNT = 3;
    private static final int TYPE_PRODUCTION = 1;
    private static final int TYPE_DISTRIBUTOR = 2;
    private static final int TYPE_OTHER = 0;
    private int mCompanyId;

    public MoviesPageAdapter(FragmentManager fm,int companyId) {
        super(fm);
        mCompanyId = companyId;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MoviesOfCompanyFragment.newInstance(TYPE_PRODUCTION,mCompanyId);
            case 1:
                return MoviesOfCompanyFragment.newInstance(TYPE_DISTRIBUTOR,mCompanyId);
            case 2:
                return MoviesOfCompanyFragment.newInstance(TYPE_OTHER,mCompanyId);
        }
        return null;
    }

    @Override
    public int getCount() {
        return MOVIE_PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "制作";
            case 1:
                return "发行";
            case 2:
                return "其他 ";
        }
        return super.getPageTitle(position);
    }
}
