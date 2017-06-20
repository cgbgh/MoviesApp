package com.app.cgb.moviepreview.ui.fragment;

import com.app.cgb.moviepreview.ui.adapter.MTimePagerAdapter;

public class MovieNewsContainFragment extends BasePagerFragment {

    @Override
    protected int getAdpaterType() {
        return MTimePagerAdapter.TYPE_NEWS_LIST;
    }

    @Override
    protected void initData() {

    }

}
