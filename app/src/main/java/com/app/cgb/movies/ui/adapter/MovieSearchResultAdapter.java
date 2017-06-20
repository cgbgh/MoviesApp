package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.SearchResult;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import butterknife.BindView;

public class MovieSearchResultAdapter extends BaseSingleTypeAdapter {

    private final int mType;
    private FootViewHolder mFooterHolder;
    private boolean mNoMoreData;

    public MovieSearchResultAdapter(Context context,int type) {
        super(context);
        setHasFooter(true);
        mType = type;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_FOOT:
                view = mInflater.inflate(R.layout.load_more_data, parent, false);
                mFooterHolder = new FootViewHolder(view);
                return mFooterHolder;
            case ITEM_NORMAL:
                view = mInflater.inflate(R.layout.movie_search_result_item, parent, false);
                return new ItemViewHolder(view);
        }
        return null;
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

    public void setNoMoreData(boolean noMoreData) {
        mNoMoreData = noMoreData;
    }

    class ItemViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_name_cn)
        TextView tvNameCn;
        @BindView(R.id.tv_name_en)
        TextView tvNameEn;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_director_constellation)
        TextView tvDirectorConstellation;
        @BindView(R.id.tv_actors_birthArea)
        TextView tvActorsBirthArea;

        public ItemViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            if (mType == Constants.SEARCH_TYPE_MOVIES){
                setupMovieResult(position);
            }else if (mType == Constants.SEARCH_TYPE_PERSONS){
                setupPersonResult(position);
            }
        }

        private void setupPersonResult(int position) {
            SearchResult.PersonsBean item = (SearchResult.PersonsBean) getItem(position);
            PicLoadUtils.loadNormalPic(mContext,item.getImg(),ivCover);
            tvNameCn.setText(TextUtils.handleEmptyText(item.getName()));
            tvNameEn.setText(TextUtils.handleEmptyText(item.getNameEn()));
            tvType.setText("职业："+TextUtils.handleEmptyText(item.getProfession()));
            tvDate.setText("生日："+TextUtils.handleEmptyText(item.getBirthday()));
            tvDirectorConstellation.setText("星座："+TextUtils.handleEmptyText(item.getConstellation()));
            tvActorsBirthArea.setText("出生地："+TextUtils.handleEmptyText(item.getBirthLocation()));
        }

        private void setupMovieResult(int position) {
            SearchResult.MoviesBean item = (SearchResult.MoviesBean) getItem(position);
            PicLoadUtils.loadNormalPic(mContext, item.getImg(), ivCover);
            tvNameCn.setText(TextUtils.handleEmptyText(item.getName()));
            tvNameEn.setText(TextUtils.handleEmptyText(item.getNameEn()));
            tvType.setText("类型："+TextUtils.handleEmptyText(item.getMovieType()));
            tvDate.setText("上映日期："+TextUtils.handleEmptyText(item.getRealTime()));
            tvDirectorConstellation.setText("导演："+TextUtils.handleListText(item.getDirectors()));
            tvActorsBirthArea.setText("主演："+TextUtils.handleListText(item.getActors()));
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
            if (mNoMoreData) {
                tvLoad.setText("貌似没有数据了");
                pbLoading.setVisibility(View.GONE);
            }
        }
    }
}
