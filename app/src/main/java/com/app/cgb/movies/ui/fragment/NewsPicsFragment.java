package com.app.cgb.moviepreview.ui.fragment;

import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.NewsDetail;
import com.app.cgb.moviepreview.ui.activity.NewsDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.NewsImgPageAdapter;
import com.app.cgb.moviepreview.utils.HtmlHelper;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import java.util.List;

import butterknife.BindView;

public class NewsPicsFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.vp_img_content)
    ViewPager vpImgContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvCount;
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.tv_show_content)
    TextView tvShowContent;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private List<NewsDetail.ImagesBean> images;
    private boolean isBarShowed = true;
    private NewsDetailActivity mActivity;
    //    private NewsDetail newsDetail;
    private int type = -1;
    private List<String> imgList;
    private String title;
    private int currentItem;
    private NewsDetail newsDetail;
    private boolean isInitial;

    public static NewsPicsFragment newInstance(int type) {
        NewsPicsFragment fragment = new NewsPicsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.NEWS_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int inflateView() {
        return R.layout.fragment_news_pics;
    }

    @Override
    protected void initView() {
        mActivity = (NewsDetailActivity) this.mContext;
        setUserVisibleHint(true);
        setupListener();
        mActivity.setupNoTitleToolbar(toolbar);
    }

    private void setupListener() {
        ivDownload.setOnClickListener(this);
        tvShowContent.setOnClickListener(this);
        final GestureDetector gesture = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                startBottomBarTranslation();
                return false;
            }
        });
        vpImgContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesture.onTouchEvent(event);
                return false;
            }
        });
        vpImgContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (images != null && images.size() > 0) {
                    tvSummary.setText(images.get(position).getDesc());
                    tvCount.setText(String.valueOf(position + 1) + "/" +
                            String.valueOf(images.size()));
                } else if (imgList != null && imgList.size() > 0) {
                    tvCount.setText(String.valueOf(position + 1) + "/" +
                            String.valueOf(imgList.size()));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void startBottomBarTranslation() {

        TransitionManager.beginDelayedTransition(rlRoot);

        RelativeLayout.LayoutParams bottomLayoutParams = (RelativeLayout.LayoutParams) llBottom.getLayoutParams();
        RelativeLayout.LayoutParams toolbarLayoutParams = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        if (isBarShowed) {
            bottomLayoutParams.addRule(RelativeLayout.BELOW, R.id.vp_img_content);
            toolbarLayoutParams.addRule(RelativeLayout.ABOVE, R.id.vp_img_content);
            isBarShowed = false;
        } else {
            bottomLayoutParams.addRule(RelativeLayout.BELOW, 0);
            toolbarLayoutParams.addRule(RelativeLayout.ABOVE, 0);
            isBarShowed = true;
        }
        llBottom.setLayoutParams(bottomLayoutParams);
        toolbar.setLayoutParams(toolbarLayoutParams);
    }

    @Override
    protected void initData() {
        type = getArguments().getInt(Constants.NEWS_TYPE);
        if (newsDetail != null) {
            setupViewWithNewsDetail();
        }
    }

    private void setupViewWithNewsDetail() {
        if (isInitial) return;
        isInitial = true;
        setupViewPager(newsDetail, type);
        tvTitle.setText(newsDetail.getTitle());
        title = newsDetail.getTitle();
    }

    public void setData(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
        if (type != -1) {
            setupViewWithNewsDetail();
        }
    }


    private void setupViewPager(NewsDetail newsDetail, int type) {
        NewsImgPageAdapter imgAdapter;
        if (type == 1) {
            images = newsDetail.getImages();
            if (images != null && images.size() > 0) {
                imgAdapter = new NewsImgPageAdapter(mContext, images);
                vpImgContent.setAdapter(imgAdapter);
                tvCount.setText(String.valueOf(vpImgContent.getCurrentItem() + 1) + "/" + images.size());
                vpImgContent.setCurrentItem(currentItem);
            }
        } else {
            HtmlHelper htmlHelper = new HtmlHelper(newsDetail.getContent());
            imgList = htmlHelper.getImgList();
            if (imgList != null && imgList.size() > 0) {
                imgAdapter = new NewsImgPageAdapter(mContext, imgList);
                vpImgContent.setAdapter(imgAdapter);
                tvCount.setText(String.valueOf(vpImgContent.getCurrentItem() + 1) + "/" +
                        String.valueOf(imgList.size()));
                vpImgContent.setCurrentItem(currentItem);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_show_content:
                mActivity.switchFragment(NewsDetailActivity.CONTENT_TAG, vpImgContent.getCurrentItem());
                break;
            case R.id.iv_download:
                downloadPic();
                break;
        }
    }

    private void downloadPic() {
        String imgUrl = "";
        int currentItem = vpImgContent.getCurrentItem();
        if (images != null) {
            NewsDetail.ImagesBean imagesBean = images.get(currentItem);
            imgUrl = imagesBean.getUrl2();
        } else if (imgList != null) {
            imgUrl = imgList.get(currentItem);
        }
        PicLoadUtils.downloadPic(mContext,imgUrl,title+currentItem);
    }


    public void setCurrentPosition(int newItem) {
        currentItem = newItem;
    }

    @Override
    public void scrollToTop() {
        vpImgContent.setCurrentItem(0);
    }

}
