package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.NewsDetail;
import com.app.cgb.moviepreview.ui.activity.MTMovieDetailActivity;
import com.app.cgb.moviepreview.ui.activity.NewsDetailActivity;
import com.app.cgb.moviepreview.ui.activity.PersonDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.ImgPageAdapter;
import com.app.cgb.moviepreview.ui.adapter.NewsImgPageAdapter;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.HtmlHelper;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import java.util.List;

import butterknife.BindView;

public class NewsContentsFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_related)
    TextView tvRelated;
    @BindView(R.id.rv_related)
    RecyclerView rvRelated;
    @BindView(R.id.related_root)
    LinearLayout relatedRoot;
    @BindView(R.id.nsv_container)
    NestedScrollView nsvContainer;
    @BindView(R.id.vp_img_content)
    ViewPager vpImgContent;

    private NewsDetailActivity mActivity;
    private HtmlHelper htmlHelper;
    private float startX;
    private float startY;
    private int type = -1;
    private boolean isInitial;
    private NewsDetail newsDetail;
    private CommonAdapter.OnItemInit onItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            final NewsDetail.RelationsBean relationsBean = (NewsDetail.RelationsBean) data.get(position);
            PicLoadUtils.loadNormalPic(mContext, relationsBean.getImage(),
                    (ImageView) holder.getView(R.id.iv_related_cover));

            holder.setText(R.id.tv_related_title, relationsBean.getName())
                    .setOnItemClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startDetailActivity(relationsBean);
                        }
                    });
        }
    };

    public static NewsContentsFragment newInstance(int type) {
        NewsContentsFragment fragment = new NewsContentsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.NEWS_TYPE,type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int inflateView() {
        return R.layout.fragment_news_contents;
    }

    @Override
    protected void initView() {
        mActivity = (NewsDetailActivity) this.mContext;
        mActivity.setupToolbar(toolbar,"News");
        setUserVisibleHint(true);
    }

    private void setupRelation(NewsDetail newsDetail) {
        List<NewsDetail.RelationsBean> relations = newsDetail.getRelations();
        if (relations != null && relations.size() > 0) {
            relatedRoot.setVisibility(View.VISIBLE);
            CommonAdapter adapter = new CommonAdapter<NewsDetail.RelationsBean>()
                    .setLayoutId(R.layout.related_item)
                    .setOnItemInit(onItemInit);
            rvRelated.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
            rvRelated.setAdapter(adapter);
            tvRelated.setText(R.string.activity_news_detail_related);
            adapter.setData(relations);
        }
    }

    @Override
    protected void initData() {
        type = getArguments().getInt(Constants.NEWS_TYPE);
        setupViewPager(type);
        if (newsDetail != null){
            setupViewWithNewsDetail(newsDetail);
        }
    }

    public void setData(NewsDetail newsDetail){
        this.newsDetail = newsDetail;
        if (type != -1){
            setupViewWithNewsDetail(newsDetail);
        }
    }

    private void setupViewWithNewsDetail(NewsDetail newsDetail) {
        if (isInitial) return;
        isInitial = true;
        setupViewPagerData(newsDetail);
        setupContent(newsDetail);
        setupRelation(newsDetail);
    }

    private void setupViewPagerData(NewsDetail newsDetail) {
        if (type == 1){
            ImgPageAdapter adapter = new NewsImgPageAdapter(mContext, newsDetail.getImages());
            vpImgContent.setAdapter(adapter);
        }
    }

    @Override
    public void scrollToTop() {
        nsvContainer.smoothScrollTo(0, 0);
    }

    private void setupContent(NewsDetail newsDetail) {
        tvTitle.setText(newsDetail.getTitle().trim());
        tvPublishTime.setText(newsDetail.getTime().trim());
        tvDesc.setText(newsDetail.getTitle2().trim());

        setupWebView(newsDetail);
    }

    private void setupWebView(final NewsDetail newsDetail) {
        final String content = newsDetail.getContent();
        WebChromeClient client = new WebChromeClient();
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebChromeClient(client);
//        webview.addJavascriptInterface(NewsContentsFragment.this,"android");
        webview.addJavascriptInterface(mActivity,"android");

        new Thread(new Runnable() {
            @Override
            public void run() {
                htmlHelper = new HtmlHelper(content);
                webview.post(new Runnable() {
                    @Override
                    public void run() {
                        webview.loadDataWithBaseURL(null, htmlHelper.getDoc().toString(), "text/html", "utf-8", null);
                    }
                });
            }
        }).start();
    }


    private void setupViewPager(int type) {
        if (type == 1) {
            vpImgContent.setVisibility(View.VISIBLE);
            vpImgContent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getRawX();
                            startY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            float endX = event.getRawX();
                            float endY = event.getRawY();
                            int touchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
                            float disX = Math.abs(endX - startX);
                            float disY = Math.abs(endY - startY);
                            if (disX < touchSlop && disY < touchSlop){
                                mActivity.switchFragment(NewsDetailActivity.PIC_TAG,
                                        vpImgContent.getCurrentItem());
                            }
                            break;
                    }
                    return false;
                }
            });
        } else {
            vpImgContent.setVisibility(View.GONE);
        }
    }

    private void startDetailActivity(NewsDetail.RelationsBean relationsBean) {
        int id = relationsBean.getId();
        int type = relationsBean.getType();
        Intent intent;
        if (type == 2) {
            intent = new Intent(mContext, PersonDetailActivity.class);
            intent.putExtra(Constants.PERSON_ID, id);
        } else {
            intent = new Intent(mContext, MTMovieDetailActivity.class);
            intent.putExtra(Constants.MOVIE_ID, id);
        }
        mContext.startActivity(intent);
    }


    @Override
    public void onPause() {
        super.onPause();
        webview.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        webview.onResume();
    }

    @Override
    public void onDestroyView() {
        webview.destroy();
        super.onDestroyView();
    }
}
