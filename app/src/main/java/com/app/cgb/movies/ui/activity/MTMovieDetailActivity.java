package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieBasicDetail;
import com.app.cgb.moviepreview.entity.MovieExtendDetail;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.MoreDetailAdapter;
import com.app.cgb.moviepreview.ui.adapter.RelatedAdapter;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.ShapeUtils;
import com.app.cgb.moviepreview.utils.TextUtils;
import com.app.cgb.moviepreview.utils.TimeUtils;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MTMovieDetailActivity extends BaseRequestActivity {

    private static final int SHAPE_SIZE = 8;
    @BindView(R.id.nsv_container)
    NestedScrollView nsvContainer;
    //  title
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

    //    基本资料
    @BindView(R.id.tv_scores)
    TextView tvScores;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_show_time)
    TextView tvShowTime;
    @BindView(R.id.tv_story)
    TextView tvStory;
    @BindView(R.id.iv_text_control)
    ImageView ivTextControl;

    //    演职员列表
    @BindView(R.id.rv_actors)
    RecyclerView rvActors;
    @BindView(R.id.ll_actors_root)
    LinearLayout llActorsRoot;

    //    图片&剧照
    @BindView(R.id.ll_pic_root)
    LinearLayout llPicRoot;
    @BindView(R.id.iv_movie_pic)
    ImageView ivMoviePic;
    @BindView(R.id.iv_video_pic)
    ImageView ivVideoPic;
    @BindView(R.id.tv_imgs_count)
    TextView tvImgCount;
    @BindView(R.id.tv_video_count)
    TextView tvVideoCount;

    //  票房
    @BindView(R.id.ll_box_root)
    LinearLayout boxRoot;
    @BindView(R.id.tv_rank)
    TextView tvRank;
    @BindView(R.id.tv_today_box)
    TextView tvTodayBox;
    @BindView(R.id.tv_today_total)
    TextView tvTodayTotal;
    @BindView(R.id.ll_box)
    LinearLayout llBox;
    @BindView(R.id.tv_total_box)
    TextView tvTotalBox;
    @BindView(R.id.tv_today_box_unit)
    TextView todayBoxUnit;
    @BindView(R.id.tv_total_box_unit)
    TextView totalBoxUnit;

    //    奖项
    @BindView(R.id.tv_award_count)
    TextView tvAwardCount;
    @BindView(R.id.ll_award_root)
    LinearLayout awardRoot;

    //    电影解读
    @BindView(R.id.news_root)
    LinearLayout newsRoot;
    @BindView(R.id.iv_news_cover)
    ImageView ivNewsCover;
    @BindView(R.id.tv_news_title)
    TextView tvNewsTitle;
    @BindView(R.id.tv_news_time)
    TextView tvNewsTime;
    @BindView(R.id.tv_news_desc)
    TextView tvNewsDesc;
    @BindView(R.id.ll_news_content)
    LinearLayout llNewsContent;

    //    events
    @BindView(R.id.events_root)
    LinearLayout eventsRoot;
    @BindView(R.id.tv_event_rank)
    TextView tvEventRank;
    @BindView(R.id.tv_event_title)
    TextView tvEventTitle;
    @BindView(R.id.tv_event_content)
    TextView tvEventContent;

    //    关联电影
    @BindView(R.id.related_root)
    LinearLayout relatedRoot;
    @BindView(R.id.rv_related)
    RecyclerView rvRelated;
    @BindView(R.id.tv_related)
    TextView tvRelated;

    //    更多资料
    @BindView(R.id.more_detail_root)
    LinearLayout moreDetailRoot;
    @BindView(R.id.rv_more)
    RecyclerView rvMore;


    private int mId;
    private boolean mIsExpanded;
    private MovieBasicDetail.DataBean data;
    private MovieExtendDetail.NewsBean news;
    private CommonAdapter.OnItemInit onActorItemInit =new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            if (holder.getItemViewType() == CommonAdapter.ITEM_HEAD){
                setupActorHead(holder);
            }else {
                setupActorItem(holder,position,data);
            }
        }
    };

    private void setupActorItem(ViewHolder holder, int position, final List data) {
        final MovieBasicDetail.DataBean.BasicBean.ActorsBean item =
                (MovieBasicDetail.DataBean.BasicBean.ActorsBean) data.get(position);
        PicLoadUtils.loadNormalPic(this, item.getImg(), (ImageView) holder.getView(R.id.iv_cover));
        holder.setText(R.id.tv_name_cn,item.getName())
                .setText(R.id.tv_name_en,item.getNameEn())
                .setText(R.id.tv_role,"饰:" + TextUtils.handleEmptyText(item.getRoleName()))
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPersonDetailActivity(item.getActorId());
                    }
                });
    }

    private void setupActorHead(ViewHolder holder) {
        final MovieBasicDetail.DataBean.BasicBean.DirectorBean director = data.getBasic().getDirector();
        if (director.getImg() != null && !director.getImg().isEmpty()) {
            PicLoadUtils.loadNormalPic(this,director.getImg(),
                    (ImageView) holder.getView(R.id.iv_cover));
        }
        holder.setText(R.id.tv_name_cn, TextUtils.handleEmptyText(director.getName()))
                .setText(R.id.tv_name_en, TextUtils.handleEmptyText(director.getNameEn()))
                .setText(R.id.tv_role,"导演")
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int directorId = director.getDirectorId();
                        startPersonDetailActivity(directorId);
                    }
                });
    }

    private void startPersonDetailActivity(int id) {
        Intent intent = new Intent(this, PersonDetailActivity.class);
        intent.putExtra(Constants.PERSON_ID, id);
        startActivity(intent);
    }

    @Override
    public int setLayoutResId() {
        return R.layout.activity_mt_movie_detail;
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

    private void setupListener() {
        nsvContainer.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float i = Math.min(scrollY, 800) / 800f;
                toolbar.getBackground().setAlpha((int) (i * 255));
                ActionBar actionBar = getSupportActionBar();
                if (actionBar!=null){

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
        mId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
    }

    public void setMovieBasicDetailView(MovieBasicDetail basicDetail) {
        data = basicDetail.getData();
        MovieBasicDetail.DataBean.BasicBean basicData = data.getBasic();
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(basicData.getName());
        }

        setupActorsList(basicData);
        setupBasicDetail(basicData);
        setupBox(basicDetail);
        setupAward(basicData);
        setupPics(basicData);
//        if (basicData.getDirector() == null || basicData.getDirector().getName() == null) {
//            mActorAdapter.setHasHeader(false);
//        }
    }

    /**
     * 处理演职员信息
     * @param basicData 基本数据
     */
    private void setupActorsList(MovieBasicDetail.DataBean.BasicBean basicData) {
        List<MovieBasicDetail.DataBean.BasicBean.ActorsBean> actors = basicData.getActors();
        if (actors != null && actors.size() > 0) {
            rvActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//            ActorListAdapter mActorAdapter = new ActorListAdapter(this, basicData.getDirector());
            CommonAdapter actorAdapter = new CommonAdapter<MovieBasicDetail.DataBean.BasicBean.ActorsBean>()
                    .setLayoutId(R.layout.actors_item)
                    .setOnItemInit(onActorItemInit)
                    .setData(actors);
            if (basicData.getDirector()!=null && basicData.getDirector().getName()!=null){
                actorAdapter.setHead(R.layout.actors_item);
            }
            rvActors.setAdapter(actorAdapter);
        }else {
            llActorsRoot.setVisibility(View.GONE);
        }
    }

    /**
     * 处理基本信息
     * @param basicData 基本数据
     */
    private void setupBasicDetail(MovieBasicDetail.DataBean.BasicBean basicData) {
        tvTitleCn.setText(TextUtils.handleEmptyText(basicData.getName()));
        tvTitleEn.setText(TextUtils.handleEmptyText(basicData.getNameEn()));
        tvStory.setText(getString(R.string.activity_movie_detail_basic_story)
                + TextUtils.handleEmptyText(basicData.getStory()));
        tvScores.setText(basicData.getOverallRating() >= 0 ?
                String.valueOf(basicData.getOverallRating()) : "");
        tvDuration.setText(getString(R.string.activity_movie_detail_basic_duration)
                + TextUtils.handleEmptyText(basicData.getMins()));
        tvType.setText(getString(R.string.activity_movie_detail_basic_type) +
                TextUtils.handleListText(basicData.getType()));
        tvShowTime.setText(getReleaseDay(basicData));
    }

    /**
     * 对基本信息的影片上映日期/地区 进行格式化
     * @param basicData 基本数据
     * @return 上映日期
     */
    private String getReleaseDay(MovieBasicDetail.DataBean.BasicBean basicData) {
        return getString(R.string.activity_movie_detail_basic_release) +
                TextUtils.formatDate(basicData.getReleaseDate(),"yyyyMMdd","yyyy年MM月dd日") +
                TextUtils.handleEmptyText(basicData.getReleaseArea());
    }

    /**
     * 处理票房信息
     * @param basicDetail
     */
    private void setupBox(MovieBasicDetail basicDetail) {
        MovieBasicDetail.DataBean.BoxOfficeBean boxOffice = basicDetail.getData().getBoxOffice();
        if (boxOffice == null) {
            boxRoot.setVisibility(View.GONE);
        } else if (boxOffice.getRanking() == 0) {
            if (boxOffice.getTotalBoxDes() != null && !boxOffice.getTotalBoxDes().trim().isEmpty()) {
                llBox.setVisibility(View.GONE);
                tvTotalBox.setVisibility(View.VISIBLE);
                tvTotalBox.setText(boxOffice.getTotalBoxUnit() + ":" + boxOffice.getTotalBoxDes());
            } else {
                boxRoot.setVisibility(View.GONE);
            }
        } else {
            tvRank.setText(boxOffice.getRanking() + "");
            tvTodayBox.setText(TextUtils.handleEmptyText(boxOffice.getTodayBoxDes()));
            todayBoxUnit.setText(TextUtils.handleEmptyText(boxOffice.getTodayBoxDesUnit()));
            tvTodayTotal.setText(TextUtils.handleEmptyText(boxOffice.getTotalBoxDes()));
            totalBoxUnit.setText(TextUtils.handleEmptyText(boxOffice.getTotalBoxUnit()));
        }
    }

    /**
     * 处理获奖情况信息
     * @param basicData
     */
    private void setupAward(MovieBasicDetail.DataBean.BasicBean basicData) {
        int totalWinAward = basicData.getAward().getTotalWinAward();
        int totalNominateAward = basicData.getAward().getTotalNominateAward();
        StringBuilder builder = new StringBuilder();
        if (totalNominateAward == 0 && totalWinAward == 0) {
            awardRoot.setVisibility(View.GONE);
        } else if (totalNominateAward == 0) {
            builder.append("共获奖").append(totalWinAward).append("次");
        } else if (totalWinAward == 0) {
            builder.append("共获提名").append(totalNominateAward).append("次");
        } else {
            builder.append("共获奖").append(totalWinAward).append("次，提名").append(totalNominateAward).append("次");
        }
        tvAwardCount.setText(builder.toString());
    }

    /**
     * 视频、剧照 图片展示处理
     * @param basicData
     */
    private void setupPics(MovieBasicDetail.DataBean.BasicBean basicData) {
        int imgCount = basicData.getStageImg().getCount();
        int videoCount = basicData.getVideo().getCount();
            tvImgCount.setText("剧照(" + imgCount + ")");
            tvVideoCount.setText("视频(" + videoCount + ")");

//            PicLoadUtils.loadCirclePic(this, basicData.getImg(), ivAvatar, Constants.MOVIE_AVATAR_SIZE);
//            PicLoadUtils.loadNormalPic(this,basicData.getImg(),ivAvatar);
            PicLoadUtils.loadCirclePic(this,basicData.getImg(),ivAvatar);
            List<MovieBasicDetail.DataBean.BasicBean.StageImgBean.ListBean> list = basicData.getStageImg().getList();
            int size = list.size();
            if (size > 0) {
                PicLoadUtils.loadNormalPic(this, list.get(3 % size).getImgUrl(), ivTitle);
                PicLoadUtils.loadNormalPic(this, list.get(1 % size).getImgUrl(), ivMoviePic);
                PicLoadUtils.loadNormalPic(this, list.get(2 % size).getImgUrl(), ivVideoPic);
            } else {
                PicLoadUtils.loadNormalPic(this, basicData.getImg(), ivTitle);
                PicLoadUtils.loadNormalPic(this, basicData.getImg(), ivMoviePic);
                PicLoadUtils.loadNormalPic(this, basicData.getImg(), ivVideoPic);
            }
        if (imgCount==0 && videoCount==0){
            llPicRoot.setVisibility(View.GONE);
        }
    }

    public void setMovieExtendDetailView(MovieExtendDetail extendDetail) {
        setupNews(extendDetail);
        setupEvents(extendDetail);
        setupRelated(extendDetail);
        setupMoreDetail(extendDetail);
    }

    /**
     * 电影解读部分处理(新闻）
     * @param extendDetail
     */
    private void setupNews(MovieExtendDetail extendDetail) {
        if (extendDetail.getNews().size() > 0 && extendDetail.getNews().get(0).getNewCount() > 0) {
            news = extendDetail.getNews().get(0);
            newsRoot.setVisibility(View.VISIBLE);
            PicLoadUtils.loadNormalPic(this, news.getImage(), ivNewsCover);
            tvNewsTitle.setText(news.getTitle());
            tvNewsDesc.setText(news.getTitle2());
            tvNewsTime.setText(TimeUtils.getPublishTime(news.getPublishTime()));
        }
    }

    /**
     * 处理  影片你必须了解的N件事 部分
     * @param extendDetail
     */
    private void setupEvents(final MovieExtendDetail extendDetail) {
        MovieExtendDetail.EventsBean events = extendDetail.getEvents();
        if (events.getEventCount() > 0 && events.getList().size() > 0) {
            eventsRoot.setVisibility(View.VISIBLE);
            tvEventTitle.setText(events.getTitle());
            tvEventContent.setText(Html.fromHtml(events.getList().get(0)));
            Drawable drawable = ShapeUtils.getOvalShape(SHAPE_SIZE, SHAPE_SIZE,
                    getResources().getColor(R.color.attr_color_accent));
            tvEventRank.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 处理 关联电影
     * @param extendDetail
     */
    private void setupRelated(MovieExtendDetail extendDetail) {
        List<MovieExtendDetail.RelelatedMovielistBean> relelatedMovielist = extendDetail.getRelelatedMovielist();
        if (relelatedMovielist != null && relelatedMovielist.size() > 0) {
            relatedRoot.setVisibility(View.VISIBLE);

            final List<MovieExtendDetail.RelelatedMovielistBean.MoviesBean> movies = new ArrayList<>();
            for (int i = 0; i < relelatedMovielist.size(); i++) {
                movies.addAll(relelatedMovielist.get(i).getMovies());
            }
            RelatedAdapter relatedAdapter = new RelatedAdapter(this, RelatedAdapter.MOVIE_RELATIONS);
            rvRelated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvRelated.setAdapter(relatedAdapter);
            relatedAdapter.setData(movies);
            tvRelated.setText("关联电影(" + movies.size() + ")");
        }

    }

    /**
     * 处理 其他资料（幕后、台词、发行制作等）
     * @param extendDetail
     */
    private void setupMoreDetail(MovieExtendDetail extendDetail) {
        MovieExtendDetail.DataBankEntryBean dataBankEntry = extendDetail.getDataBankEntry();
        if (dataBankEntry != null) {
            if (dataBankEntry.isIsClassicLines() || dataBankEntry.isIsMediaReview()
                    || dataBankEntry.isIsBehind() || dataBankEntry.isIsCompany()) {
                moreDetailRoot.setVisibility(View.VISIBLE);
                MoreDetailAdapter moredetailAdapter = new MoreDetailAdapter(this, mId);
                GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                rvMore.setLayoutManager(layoutManager);
                rvMore.setAdapter(moredetailAdapter);
                moredetailAdapter.setData(dataBankEntry);
            }
        }
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadBasicDetail(mId);
        model.loadExtendDetail(mId);
    }

    @Override
    public void showLoading() {
        nsvContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        nsvContainer.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.iv_video_pic, R.id.video_root, R.id.pic_root, R.id.news_root,
            R.id.ll_news_content, R.id.events_root, R.id.actors_all,
            R.id.ll_story_container, R.id.ll_award_root})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_video_pic:
                if (data.getBasic().getVideo().getCount()>0){
                    startVideoPlayer();
                }
                break;
            case R.id.video_root:
                if (data.getBasic().getVideo().getCount()>0){
                    startActivityWithIntExtra(VideoListActivtity.class, Constants.MOVIE_ID,mId);
                }
                break;
            case R.id.pic_root:
                if (data.getBasic().getStageImg().getCount()>0){
                    startActivityWithIntExtra(PicActivity.class, Constants.MOVIE_ID,mId);
                }
                break;
            case R.id.news_root:
                startActivityWithIntExtra(MovieNewsActivity.class, Constants.MOVIE_ID,mId);
                break;
            case R.id.ll_news_content:
                startNewsDetailActivity();
//                startActivityWithIntExtra(NewsDetailActivity.class, Constants.NEWS_ID,news.getId());
                break;
            case R.id.events_root:
                startActivityWithIntExtra(EventsActivity.class, Constants.MOVIE_ID,mId);
                break;
            case R.id.actors_all:
                startActivityWithIntExtra(CreditsActivity.class, Constants.MOVIE_ID,mId);
                break;
            case R.id.ll_story_container:
                expandText();
                break;
            case R.id.ll_award_root:
                AwardsActivity.setMovieAwards(data.getBasic());
                startActivityWithIntExtra(AwardsActivity.class,null,mId);
                break;
        }
    }

    private void startNewsDetailActivity() {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_ID, news.getId());
        intent.putExtra(Constants.NEWS_TYPE,news.getType());
        startActivity(intent);
    }

    private void startVideoPlayer() {
        Intent videoIntent = new Intent(MTMovieDetailActivity.this, SystemPlayer.class);
        String hightUrl = data.getBasic().getVideo().getHightUrl();
        if (hightUrl == null || hightUrl.isEmpty()) {
            String url = data.getBasic().getVideo().getUrl();
            if (url != null && !url.isEmpty()) {
                videoIntent.setData(Uri.parse(url));
                startActivity(videoIntent);
            }
        } else {
            videoIntent.setData(Uri.parse(hightUrl));
            startActivity(videoIntent);
        }
    }

    private void startActivityWithIntExtra(Class activity, String key, int value) {
        Intent intent = new Intent(MTMovieDetailActivity.this, activity);
        if (key!=null){
            intent.putExtra(key,value);
        }
        startActivity(intent);
    }

    private void expandText() {
        if (!mIsExpanded) {
            tvStory.setMaxLines(Integer.MAX_VALUE);
            ivTextControl.setImageResource(R.drawable.ic_expand_less_black_12dp);
            mIsExpanded = !mIsExpanded;
        } else {
            tvStory.setMaxLines(2);
            ivTextControl.setImageResource(R.drawable.ic_expand_more_black_12dp);
            mIsExpanded = !mIsExpanded;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
//        重置toolbar透明度
        toolbar.getBackground().setAlpha(255);
    }

    @Override
    protected void onResponse(Object response) {
        if (response instanceof MovieBasicDetail){
            setMovieBasicDetailView(((MovieBasicDetail) response));
        }else if (response instanceof MovieExtendDetail){
            setMovieExtendDetailView(((MovieExtendDetail) response));
        }
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
        int y = Math.min(nsvContainer.getScrollY(), 800);
        float ratio = y/800f;
        toolbar.getBackground().setAlpha((int) (ratio*255));
    }

}
