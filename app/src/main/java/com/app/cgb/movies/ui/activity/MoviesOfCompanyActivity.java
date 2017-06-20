package com.app.cgb.moviepreview.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseActivity;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.ui.adapter.MoviesPageAdapter;

import butterknife.BindView;

public class MoviesOfCompanyActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_movies_of_company;
    }

    @Override
    protected void initView() {
        setupNoTitleToolbar(toolbar);
    }

    private void setupViewPager(int companyId) {
        MoviesPageAdapter adapter = new MoviesPageAdapter(getSupportFragmentManager(),companyId);
        vpContent.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContent);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        int companyId = getIntent().getIntExtra(Constants.COMPANY_ID, -1);
        String companyName = getIntent().getStringExtra(Constants.COMPANY_NAME);
        toolbarTitle.setText(companyName);
        setupViewPager(companyId);
    }
}
