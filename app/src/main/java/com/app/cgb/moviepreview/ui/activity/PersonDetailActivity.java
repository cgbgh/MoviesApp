package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.PersonsDetail;
import com.app.cgb.moviepreview.entity.PersonsRelation;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.ExpAdapter;
import com.app.cgb.moviepreview.ui.adapter.PersonPicAdapter;
import com.app.cgb.moviepreview.ui.adapter.RelatedAdapter;
import com.app.cgb.moviepreview.ui.view.MyScrollView;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.SizeUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonDetailActivity extends BaseRequestActivity {

    @BindView(R.id.nsv_root)
    MyScrollView nsvRoot;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    //    title
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title_cn)
    TextView tvTitleCn;
    @BindView(R.id.tv_title_en)
    TextView tvTitleEn;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

    //    basic detail
    @BindView(R.id.tv_scores)
    TextView tvScores;
    @BindView(R.id.tv_duration)
    TextView tvProfession;
    @BindView(R.id.tv_type)
    TextView tvAddress;
    @BindView(R.id.tv_show_time)
    TextView tvBirthDay;
    @BindView(R.id.ll_story_container)
    LinearLayout storyContainer;
    @BindView(R.id.tv_story)
    TextView tvStory;

    //    awards
    @BindView(R.id.tv_award_count)
    TextView tvAwardCount;
    @BindView(R.id.ll_award_root)
    LinearLayout llAwardRoot;

    //    person pics
    @BindView(R.id.rv_pics)
    RecyclerView rvPics;
    @BindView(R.id.ll_person_pic_root)
    LinearLayout llPersonPicRoot;

    //    experiences
    @BindView(R.id.rv_experiences)
    RecyclerView rvExperiences;
    @BindView(R.id.ll_experiences_root)
    LinearLayout llExperiencesRoot;

    //    related movie
    @BindView(R.id.tv_related)
    TextView tvRelated;
    @BindView(R.id.rv_related)
    RecyclerView rvRelated;
    @BindView(R.id.related_root)
    LinearLayout relatedRoot;

    //    单位：dp
    private static final float TITLE_PIC_SIZE = 240;

    private int mPersonId;
    private boolean storyExpanded;
    private PersonsDetail.DataBean.BackgroundBean backgroundBean;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_person_detail;
    }

    @Override
    protected void initView() {
        setupNoTitleToolbar(toolbar);
        setupListener();
    }

    @Override
    public void setupNoTitleToolbar(Toolbar toolbar) {
        super.setupNoTitleToolbar(toolbar);
        Drawable background = toolbar.getBackground();
        background.setAlpha(0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupListener() {
        nsvRoot.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float i = Math.min(scrollY, 800) / 800f;
                toolbar.getBackground().setAlpha((int) (i * 255));
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {

                    if (scrollY >= 500) {
                        actionBar.setDisplayShowTitleEnabled(true);
                    } else {
                        actionBar.setDisplayShowTitleEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPersonId = getIntent().getIntExtra(Constants.PERSON_ID, -1);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadPersonDetail(mPersonId);
        model.loadPersonRelations(mPersonId,1);
    }

    @Override
    protected void onResponse(Object response) {
        if (response instanceof  PersonsDetail){
            setPersonDetailData((PersonsDetail) response);
        }else if (response instanceof List){
            setPersonRelationsData((List<PersonsRelation>) response);
        }
    }

    public void setPersonDetailData(PersonsDetail personDetailData) {
        if (personDetailData.getData() != null && personDetailData.getData().getBackground() != null) {
            backgroundBean = personDetailData.getData().getBackground();
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(backgroundBean.getNameCn());
            }
            loadPics(backgroundBean);
            setupText(backgroundBean);
            setupAwards(backgroundBean);
            setupExperiences(backgroundBean);
        }
    }

    private void loadPics(PersonsDetail.DataBean.BackgroundBean backgroundBean) {
        List<PersonsDetail.DataBean.BackgroundBean.ImagesBean> images = backgroundBean.getImages();
        if (images != null && images.size() > 0) {
            setupPersonPics(images);

            PicLoadUtils.loadTranslationPic(this, images.get(0 % images.size()).getImage(), ivTitle,
                    SizeUtils.getScreenWidth(this), SizeUtils.dp2px(this, TITLE_PIC_SIZE));

        } else if (backgroundBean.getImage() != null && !backgroundBean.getImage().isEmpty()) {
            PicLoadUtils.loadTranslationPic(this, backgroundBean.getImage(), ivTitle,
                    SizeUtils.getScreenWidth(this), SizeUtils.dp2px(this, TITLE_PIC_SIZE));
        }
        PicLoadUtils.loadCirclePic(this, backgroundBean.getImage(), ivAvatar);

    }

    /**
     * 设置影人图集列表
     *
     * @param images
     */
    private void setupPersonPics(List<PersonsDetail.DataBean.BackgroundBean.ImagesBean> images) {
        llPersonPicRoot.setVisibility(View.VISIBLE);
        rvPics.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PersonPicAdapter personPicAdapter = new PersonPicAdapter(this);
        rvPics.setAdapter(personPicAdapter);
        personPicAdapter.setData(images);
    }

    /**
     * 处理人物基本信息文本
     * @param backgroundBean
     */
    private void setupText(PersonsDetail.DataBean.BackgroundBean backgroundBean) {
        tvTitleCn.setText(TextUtils.handleEmptyText(backgroundBean.getNameCn()));
        tvTitleEn.setText(TextUtils.handleEmptyText(backgroundBean.getNameEn()));
        tvProfession.setText("职业：" + TextUtils.handleEmptyText(backgroundBean.getProfession()));
        tvAddress.setText("出生地：" + TextUtils.handleEmptyText(backgroundBean.getAddress()));
        String birthday = TextUtils.getBirthDay(backgroundBean.getBirthYear(),
                backgroundBean.getBirthMonth(),
                backgroundBean.getBirthDay());
        tvBirthDay.setText("生日："+birthday);
        tvStory.setText("简介：" + TextUtils.handleEmptyText(backgroundBean.getContent()));
    }

    /**
     * 处理人物获奖情况信息
     * @param backgroundBean
     */
    private void setupAwards(PersonsDetail.DataBean.BackgroundBean backgroundBean) {
        StringBuilder awards = new StringBuilder();
        int totalNominateAward = backgroundBean.getTotalNominateAward();
        int totalWinAward = backgroundBean.getTotalWinAward();
        if (totalNominateAward != 0 && totalWinAward != 0) {
            awards.append("共获奖").append(totalWinAward).append("次,")
                    .append("提名").append(totalNominateAward).append("次");
        } else if (totalWinAward != 0) {
            awards.append("共获奖").append(totalWinAward).append("次");
        } else if (totalNominateAward != 0) {
            awards.append("共获提名").append(totalNominateAward).append("次");
        } else {
            llAwardRoot.setVisibility(View.GONE);
        }
        tvAwardCount.setText(awards);
    }

    /**
     * 处理人物经历信息
     * @param backgroundBean
     */
    private void setupExperiences(PersonsDetail.DataBean.BackgroundBean backgroundBean) {
        if (backgroundBean.getExpriences() != null && backgroundBean.getExpriences().size() > 0) {
            llExperiencesRoot.setVisibility(View.VISIBLE);
            ExpAdapter expAdapter = new ExpAdapter(this);
            rvExperiences.setLayoutManager(new LinearLayoutManager(this));
            rvExperiences.setAdapter(expAdapter);
            expAdapter.setData(backgroundBean.getExpriences());
        }
    }

    public void setPersonRelationsData(List<PersonsRelation> personRelationsData) {
        if (personRelationsData.size() > 0) {
            setupRelationList(personRelationsData);
        }
    }

    private void setupRelationList(final List<PersonsRelation> personRelationsData) {
        relatedRoot.setVisibility(View.VISIBLE);
        PersonsRelation personsRelation = personRelationsData.get(0);
        if (personsRelation!=null){
            int totalCount = personsRelation.getTotalCount();
            tvRelated.setText(new StringBuilder("相关作品(").append(totalCount).append(")"));
        }
        RelatedAdapter relatedAdapter = new RelatedAdapter(this, RelatedAdapter.PERSON_RELATIONS);
        rvRelated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRelated.setAdapter(relatedAdapter);
        relatedAdapter.setData(personRelationsData);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        nsvRoot.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        pbLoading.setVisibility(View.GONE);
        nsvRoot.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        toolbar.getBackground().setAlpha(255);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetToolbar();
    }

    /**
     * 回复toolbar透明度属性
     */
    private void resetToolbar() {
        int y = Math.min(nsvRoot.getScrollY(), 800);
        float ratio = y / 800f;
        toolbar.getBackground().setAlpha((int) (ratio * 255));
    }

    @OnClick({R.id.ll_story_container, R.id.ll_award_root, R.id.ll_person_pic_root})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_story_container:
                if (storyExpanded) {
                    tvStory.setMaxLines(2);
                    storyExpanded = !storyExpanded;
                } else {
                    tvStory.setMaxLines(Integer.MAX_VALUE);
                    storyExpanded = !storyExpanded;
                }
                break;
            case R.id.ll_award_root:
                AwardsActivity.setPersonAwards(backgroundBean);
                Intent awardsIntent = new Intent(PersonDetailActivity.this, AwardsActivity.class);
                startActivity(awardsIntent);
                break;
            case R.id.ll_person_pic_root:
                Intent intent = new Intent(PersonDetailActivity.this, PicActivity.class);
                intent.putExtra(Constants.PERSON_ID, mPersonId);
                startActivity(intent);
                break;
        }
    }
}
