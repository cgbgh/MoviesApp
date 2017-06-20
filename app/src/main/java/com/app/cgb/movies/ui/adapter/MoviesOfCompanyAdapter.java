package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MoviesOfCompany;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import butterknife.BindView;

public class MoviesOfCompanyAdapter extends BaseSingleTypeAdapter<MoviesOfCompany.MoviesBean> {

    private boolean mNoMoreData;
    private FootViewHolder mFootHolder;

    public MoviesOfCompanyAdapter(Context context) {
        super(context);
        setHasFooter(true);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_FOOT:
                view = mInflater.inflate(R.layout.load_more_data, parent, false);
                mFootHolder = new FootViewHolder(view);
                return mFootHolder;
            case ITEM_NORMAL:
                view = mInflater.inflate(R.layout.movie_of_company_item, parent, false);
                return new ItemViewHolder(view);
        }
        return null;
    }


    public void startLoading() {
        if (mFootHolder != null) {
            mFootHolder.tvLoad.setVisibility(View.GONE);
            mFootHolder.pbLoading.setVisibility(View.VISIBLE);
        }
    }

    public void stopLoading() {
        if (mFootHolder != null) {
            mFootHolder.pbLoading.setVisibility(View.GONE);
            mFootHolder.tvLoad.setVisibility(View.VISIBLE);
        }
    }

    public void setNoMoreData(boolean noMoreData) {
        mNoMoreData = noMoreData;
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

    class ItemViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_title_en)
        TextView tvTitleEn;
        @BindView(R.id.tv_director)
        TextView tvDirector;
        @BindView(R.id.tv_actors)
        TextView tvActors;
        @BindView(R.id.tv_date_area)
        TextView tvDateArea;
        @BindView(R.id.tv_scores)
        TextView tvScores;

        public ItemViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            MoviesOfCompany.MoviesBean item = getItem(position);
            if (!item.getImg().isEmpty()){
                PicLoadUtils.loadNormalPic(mContext,item.getImg(),ivCover);
            }else {
                PicLoadUtils.loadErrorPic(mContext,ivCover);
            }
            tvTitle.setText(item.getName());
            tvTitleEn.setText(item.getNameEn());
            tvDirector.setText("导演："+item.getDirector());
            tvActors.setText("主演："+item.getActor1()+"/"+item.getActor2());
            tvDateArea.setText("上映日期/地区："+item.getReleaseDate()+"/"+item.getReleaseArea());
            if (item.getRating()>0){
                tvScores.setText(String.valueOf(item.getRating()));
            }
        }
    }
}
