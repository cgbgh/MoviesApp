package com.app.cgb.moviepreview.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.ui.adapter.MTimePagerAdapter;

import butterknife.BindView;


public abstract class BasePagerFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private MTimePagerAdapter mAdapter;

    @Override
    protected int inflateView() {
        return R.layout.fragment_mtime;
    }


    @Override
    protected void initView() {
        FragmentManager fm = getChildFragmentManager();
        mAdapter = new MTimePagerAdapter(fm, getAdpaterType());
        vpContent.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(vpContent);
    }

    protected abstract int getAdpaterType();

    @Override
    public void scrollToTop() {
        BaseFragment childFragment = getCurrentChild();
        childFragment.scrollToTop();
    }

    private BaseFragment getCurrentChild() {
        int currentItem = vpContent.getCurrentItem();
        return (BaseFragment) mAdapter.getItem(currentItem);
    }

}
