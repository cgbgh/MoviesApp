package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseAdapter;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieNews;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.SizeUtils;
import com.app.cgb.moviepreview.utils.TextUtils;
import com.app.cgb.moviepreview.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;

public class NewsAdpater extends BaseAdapter<MovieNews.NewsListBean> {

    private static final int TYPE_SINGLE_PIC = 0;
    private static final int TYPE_BIG_PIC = 1;
    private static final int TYPE_HEAD = 3;
    private static final int TYPE_FOOT = 4;
    public static final int FROM_NEWS_LIST = 101;
    public static final int FROM_MOVIE = 102;
    private OnItemClickListener mListener;
    private FootViewHolder mFooterHolder;
    private int mFrom;
    private int parentWidth;
    private boolean mNoMoreData;

    public NewsAdpater(Context context, int from) {
        super(context);
        mFrom = from;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        parentWidth = parent.getWidth();
        switch (viewType) {
            case TYPE_HEAD:
                view = mInflater.inflate(R.layout.movie_news_header, parent, false);
                return new HeaderViewHolder(view);
            case TYPE_SINGLE_PIC:
                view = mInflater.inflate(R.layout.movie_news_type_single_pic, parent, false);
                return new TypeSinglePicViewHolder(view);
            case TYPE_BIG_PIC:
                view = mInflater.inflate(R.layout.movie_news_big_pic, parent, false);
                return new BigPicViewHolder(view);
            case TYPE_FOOT:
                view = mInflater.inflate(R.layout.load_more_data, parent, false);
                mFooterHolder = new FootViewHolder(view);
                return mFooterHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        holder.fillView(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == mListData.size()) {
                    footCilck();
                } else {
                    itemClick(position);
                }
            }
        });

    }

    public void startLoading() {
        if (mFooterHolder != null) {
            mFooterHolder.tvLoad.setVisibility(View.GONE);
            mFooterHolder.pbLoading.setVisibility(View.VISIBLE);
        }
    }

    public void stopLoading() {
        if (mFooterHolder != null) {
            mFooterHolder.pbLoading.setVisibility(View.GONE);
            mFooterHolder.tvLoad.setVisibility(View.VISIBLE);
        }
    }

    public MovieNews.NewsListBean getItem(int position) {
        if (position < 0 || position >= mListData.size()) {
            return null;
        }
        return mListData.get(position);
    }

    public FootViewHolder getFooter() {
        if (mFooterHolder != null) {
            return mFooterHolder;
        }
        return null;
    }

    private void itemClick(int position) {
        if (mListener != null) {
            mListener.onItemClick(position);
        }
    }

    private void footCilck() {
        if (mListener != null) {
            mListener.onFootClick();
        }
    }

    @Override
    public int getItemCount() {
        if (mListData.size() == 0) {
            return 0;
        }
        return mListData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mFrom == FROM_NEWS_LIST && position == 0) {
            return TYPE_HEAD;
        } else if (position == mListData.size()) {
            return TYPE_FOOT;
        }

        switch (mListData.get(position).getType()) {
            case 0:
            case 2:
                return TYPE_SINGLE_PIC;
            case 1:
                return TYPE_BIG_PIC;
        }

        return RecyclerView.NO_POSITION;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setNoMoreData(boolean noMoreData) {
        mNoMoreData = noMoreData;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onFootClick();
    }

    class HeaderViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title_cn)
        TextView tvTitle;

        public HeaderViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            MovieNews.NewsListBean newsListBean = mListData.get(position);
//            PicLoadUtils.loadNormalPic(mContext, newsListBean.getImage(), ivCover);
            PicLoadUtils.loadTranslationPic(mContext, newsListBean.getImage(), ivCover,parentWidth, SizeUtils.dp2px(mContext,200));

            tvTitle.setText(newsListBean.getTitle());
        }
    }

    class TypeSinglePicViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title_cn)
        TextView tvTitle;
        @BindView(R.id.tv_publish_time)
        TextView tvPublishTime;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_tag)
        TextView tvTag;

        public TypeSinglePicViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            MovieNews.NewsListBean newsListBean = mListData.get(position);
            PicLoadUtils.loadNormalPic(mContext, newsListBean.getImage(), ivCover);
            tvTitle.setText(newsListBean.getTitle());
            tvPublishTime.setText(TextUtils.handleEmptyText(
                    TimeUtils.getPublishTime(newsListBean.getPublishTime())));
            tvDesc.setText(TextUtils.handleEmptyText(newsListBean.getTitle2()));
            tvTag.setText(TextUtils.handleEmptyText(newsListBean.getSummary()));
        }
    }

    class FootViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_load)
        TextView tvLoad;
        @BindView(R.id.pb_loading)
        ProgressBar pbLoading;

        public FootViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            if (mNoMoreData){
                tvLoad.setText("貌似没有数据了");
                pbLoading.setVisibility(View.GONE);
            }
        }
    }


    class BigPicViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title_cn)
        TextView tvTitle;
        @BindView(R.id.tv_publish_time)
        TextView tvPublishTime;

        public BigPicViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            MovieNews.NewsListBean newsListBean = mListData.get(position);
            List<MovieNews.NewsListBean.ImagesBean> images = newsListBean.getImages();
            if (images != null && images.size()>0 ){
                PicLoadUtils.loadNormalPic(mContext, images.get(0).getUrl2(), ivCover);
            }
            tvTitle.setText(newsListBean.getTitle());
            tvPublishTime.setText(TextUtils.handleEmptyText(
                    TimeUtils.getPublishTime(newsListBean.getPublishTime())));
        }
    }
}