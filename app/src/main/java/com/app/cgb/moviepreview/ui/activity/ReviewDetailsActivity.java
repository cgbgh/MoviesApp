package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.ReviewDetails;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.utils.HtmlHelper;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import butterknife.BindView;

public class ReviewDetailsActivity extends BaseRequestActivity<ReviewDetails> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.review_content)
    NestedScrollView reviewContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_movie_name)
    TextView tvMovieName;
    @BindView(R.id.iv_movie_pic)
    ImageView ivMoviePic;
    @BindView(R.id.wv_content)
    WebView wvContent;
    private int reviewId;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_review_detail;
    }

    @Override
    protected void initView() {
        setupNoTitleToolbar(toolbar);
        setupWebView();
    }

    private void setupWebView() {
        WebViewClient client = new WebViewClient();
        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        wvContent.setWebViewClient(client);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        reviewId = getIntent().getIntExtra(Constants.REVIEW_ID, -1);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadReviewDetail(reviewId);
    }

    @Override
    protected void onResponse(ReviewDetails reviewDetails) {
        setupHead(reviewDetails);
        setupWebData(reviewDetails);
    }

    private void setupWebData(ReviewDetails reviewDetailData) {
//        String html = NetWorkUtil.getFormatedHtml(reviewDetailData.getContent());
        HtmlHelper htmlHelper = new HtmlHelper(reviewDetailData.getContent());
        wvContent.loadDataWithBaseURL(null,htmlHelper.getDoc().toString(),"text/html","utf-8",null);
    }

    private void setupHead(ReviewDetails reviewDetailData) {
        tvTitle.setText(reviewDetailData.getTitle().trim());
        tvPublishTime.setText(reviewDetailData.getTime());
        PicLoadUtils.loadCirclePic(this,reviewDetailData.getUserImage(),ivAvatar);
        tvUserName.setText(new StringBuilder()
                .append(reviewDetailData.getNickname()).append(" 评"));
        final ReviewDetails.RelatedObjBean relatedObj = reviewDetailData.getRelatedObj();
        if (relatedObj!=null){
            PicLoadUtils.loadNormalPic(this,relatedObj.getImage(),ivMoviePic);
            tvMovieName.setText(relatedObj.getTitle());
            ivMoviePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startMovieDetailActivity(relatedObj.getId());
                }
            });
        }
    }

    private void startMovieDetailActivity(int id) {
        Intent intent = new Intent(this, MTMovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID,id);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        reviewContent.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        reviewContent.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }

}
