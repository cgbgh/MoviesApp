package com.app.cgb.moviepreview.ui.fragment;

import com.app.cgb.moviepreview.ui.adapter.MTimePagerAdapter;

public class MTimeFragment extends BasePagerFragment {

    @Override
    protected int getAdpaterType() {
        return MTimePagerAdapter.TYPE_MOVIE_LIST;
    }

    @Override
    protected void initData() {

    }

}
