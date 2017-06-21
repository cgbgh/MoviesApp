package com.app.cgb.moviepreview.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseActivity;
import com.app.cgb.moviepreview.entity.AwardsBean;
import com.app.cgb.moviepreview.entity.MovieBasicDetail;
import com.app.cgb.moviepreview.entity.PersonsDetail;
import com.app.cgb.moviepreview.ui.adapter.treeadapter.AwardFestivalItem;
import com.app.cgb.moviepreview.ui.adapter.treeadapter.TreeAdapter;
import com.app.cgb.moviepreview.ui.adapter.treeadapter.TreeAdapterItem;
import com.app.cgb.moviepreview.ui.view.LinearDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class AwardsActivity extends BaseActivity {
    private static MovieBasicDetail.DataBean.BasicBean movieAwards;
    private static PersonsDetail.DataBean.BackgroundBean personAwards;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_awards)
    RecyclerView rvAwards;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_awards;
    }

    public static void setMovieAwards(MovieBasicDetail.DataBean.BasicBean basicBean) {
        movieAwards = basicBean;
    }

    public static void setPersonAwards(PersonsDetail.DataBean.BackgroundBean backgroundBean) {
        personAwards = backgroundBean;
    }

    @Override
    protected void initView() {
        initToolbar();
        setupAwardsList();
//        setTranslationListener();
    }


    private void setupAwardsList() {
        TreeAdapter treeAdapter = new TreeAdapter();
        rvAwards.setLayoutManager(new LinearLayoutManager(this));
        rvAwards.setItemAnimator(new DefaultItemAnimator());
        rvAwards.setAdapter(treeAdapter);
        rvAwards.addItemDecoration(new LinearDecoration(
                LinearDecoration.VERTICAL_LIST, new LinearDecoration.OnDecorationDraw() {
            @Override
            public Drawable getDecration(int position) {
                return getResources().getDrawable(R.drawable.shape_line);
            }

            @Override
            public boolean shouldOffset(View view, RecyclerView parent) {
                return true;
            }
        }));

        if (movieAwards != null) {
            setMovieDatas(treeAdapter);
        } else if (personAwards != null) {
            setPersonDatas(treeAdapter);
        }

    }

    private void setPersonDatas(TreeAdapter treeAdapter) {
        List<? extends AwardsBean.Awards> awards = personAwards.getAwards();
        if (awards != null && awards.size() > 0) {
            ArrayList<TreeAdapterItem> awardFestivalItems = new ArrayList<>();
            for (int i = 0; i < awards.size(); i++) {
                AwardFestivalItem awardFestivalItem = new AwardFestivalItem(awards.get(i), this);
                awardFestivalItem.setFestivals(personAwards.getFestivals().get(i));
                awardFestivalItems.add(awardFestivalItem);
            }
            treeAdapter.setBranches(awardFestivalItems);
        }
    }

    private void setMovieDatas(TreeAdapter treeAdapter) {
        MovieBasicDetail.DataBean.BasicBean.AwardBean awards = movieAwards.getAward();
        if (awards != null) {
            List<MovieBasicDetail.DataBean.BasicBean.AwardBean.AwardListBean> awardList = awards.getAwardList();
            if (awardList != null && awardList.size() > 0) {
                ArrayList<TreeAdapterItem> awardFestivalItems = new ArrayList<>();
                for (int i = 0; i < awardList.size(); i++) {
                    AwardFestivalItem awardFestivalItem = new AwardFestivalItem(awardList.get(i), this);
                    awardFestivalItem.setFestivals(movieAwards.getFestivals().get(i));
                    awardFestivalItems.add(awardFestivalItem);
                }
                treeAdapter.setBranches(awardFestivalItems);
            }
        }
    }

    private void initToolbar() {
        setupToolbar(toolbar, "获奖&提名");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

}
