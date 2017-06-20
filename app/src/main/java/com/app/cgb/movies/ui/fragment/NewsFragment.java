package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieNews;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.activity.NewsDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.SizeUtils;
import com.app.cgb.moviepreview.utils.TextUtils;
import com.app.cgb.moviepreview.utils.TimeUtils;
import com.app.cgb.rlrecyclerview.DividerItemDecoration;
import com.app.cgb.rlrecyclerview.RLRecyclerView;

import java.util.List;

public class NewsFragment extends BaseRefreshFragment<MovieNews> implements
        RLRecyclerView.OnLoadListener, CommonAdapter.MultiTypeSupport,
        CommonAdapter.OnItemClickListener {

    private static final int TYPE_HEAD = 100;
    private static final int TYPE_BIG_PIC = 101;
    private static final int TYPE_NORMAL = 102;
    private int mPageIndex = 1;
    private boolean isRefresh;
    private boolean isLoadMore;
    private CommonAdapter<MovieNews.NewsListBean> adapter;

    @Override
    protected void initView() {
        super.initView();
        setupList();
    }

    private void setupList() {
        getRvList().setCanLoadMore(true);
        getRvList().addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        getRvList().setOnLoadListener(this);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        adapter = new CommonAdapter<>();
        adapter.setMultiTypeSupport(this)
                .setOnItemClickListener(this);
        return adapter;
    }

    @Override
    protected void initData() {
        reset();
        super.initData();
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadMovieNews(mPageIndex);
    }

    @Override
    protected void reset() {
        isRefresh = true;
        mPageIndex = 1;
    }

    @Override
    public void showLoading() {
        if (isRefresh){
            super.showLoading();
        }
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        if (isRefresh) {
            isRefresh = false;
            super.hideLoading(isSuccess);
        }
        if (isLoadMore){
            isLoadMore = false;
            if (isSuccess){
                rvList.loadComplete();
            }else {
                rvList.loadError();
            }
        }
    }

    @Override
    public void onResponse(MovieNews movieNews) {
        if (movieNews.getNewsList() == null || movieNews.getNewsList().size() < 20) {
            rvList.setNoMoreData(true);
        }
        if (isRefresh) {
            adapter.setData(movieNews.getNewsList());
            mPageIndex++;
            return;
        }
        if (isLoadMore) {
            adapter.addData(movieNews.getNewsList());
        }
        mPageIndex++;
    }

    @Override
    public boolean onLoad() {
        request();
        isLoadMore = true;
        return true;
    }

    @Override
    public int getItemType(int position) {
        List<MovieNews.NewsListBean> datas = adapter.getDatas();
        if (position == 0) {
            return TYPE_HEAD;
        }
        if (datas.get(position).getType() == 1){
            return TYPE_BIG_PIC;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getLayoutId(int viewType) {
        switch (viewType){
            case TYPE_HEAD:
                return R.layout.movie_news_header;
            case TYPE_BIG_PIC:
                return R.layout.movie_news_big_pic;
            default:
                return R.layout.movie_news_type_single_pic;
        }
    }

    @Override
    public void onBind(ViewHolder holder, int position, List data) {
        MovieNews.NewsListBean item = (MovieNews.NewsListBean) data.get(position);
        int type = holder.getItemViewType();
        switch (type){
            case TYPE_NORMAL:
                setupNormalItem(holder,item);
                break;
            case TYPE_BIG_PIC:
                setupBigPicItem(holder,item);
                break;
            case TYPE_HEAD:
                setupHead(holder, item);
                break;

        }
    }

    private void setupBigPicItem(ViewHolder holder, MovieNews.NewsListBean item) {
        List<MovieNews.NewsListBean.ImagesBean> images = item.getImages();
        ImageView ivCover = holder.getView(R.id.iv_cover);
        if (images != null && images.size()>0 ){
            PicLoadUtils.loadNormalPic(mContext, images.get(0).getUrl2(), ivCover);
        }else if (item.getImage() != null){
            PicLoadUtils.loadNormalPic(mContext,item.getImage(),ivCover);
        }
        holder.setText(R.id.tv_title_cn,item.getTitle())
                .setText(R.id.tv_publish_time,TextUtils.handleEmptyText(
                        TimeUtils.getPublishTime(item.getPublishTime())));
    }

    private void setupNormalItem(ViewHolder holder, MovieNews.NewsListBean item) {
        PicLoadUtils.loadNormalPic(mContext, item.getImage(),
                (ImageView) holder.getView(R.id.iv_cover));
        holder.setText(R.id.tv_title_cn,item.getTitle())
                .setText(R.id.tv_publish_time,TextUtils.handleEmptyText(
                        TimeUtils.getPublishTime(item.getPublishTime())))
                .setText(R.id.tv_desc,TextUtils.handleEmptyText(item.getTitle2()))
                .setText(R.id.tv_tag,TextUtils.handleEmptyText(item.getSummary()));
    }

    private void setupHead(ViewHolder holder, MovieNews.NewsListBean item) {
        ImageView ivCover = holder.getView(R.id.iv_cover);
        PicLoadUtils.loadTranslationPic(mContext,
                item.getImage(), ivCover,
                SizeUtils.getScreenWidth(mContext),
                SizeUtils.dp2px(mContext,200));
        holder.setText(R.id.tv_title_cn,item.getTitle());
    }

    @Override
    public void onItemClick(ViewHolder holder, int position) {
        MovieNews.NewsListBean newsListBean = adapter.getDatas().get(position);
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_ID, newsListBean.getId());
        intent.putExtra(Constants.NEWS_TYPE, newsListBean.getType());
        mContext.startActivity(intent);
    }
}
