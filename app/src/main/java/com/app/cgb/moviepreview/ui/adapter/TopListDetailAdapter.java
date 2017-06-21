package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.entity.TopListDetails;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.ShapeUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TopListDetailAdapter extends BaseSingleTypeAdapter {


    private FootViewHolder mFootViewHolder;
    private String mHeadDesc;
    private String mHeadTitle;
    private int mType;
    private List<TopListDetails.MoviesBean> mMovies = new ArrayList<>();
    private List<TopListDetails.PersonsBean> mPersons = new ArrayList<>();
    private boolean mNoMoreData;
    private boolean mNoData;

    public TopListDetailAdapter(Context context) {
        super(context);
        setHasFooter(true);
        setHasHeader(true);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_HEAD:
                view = mInflater.inflate(R.layout.toplist_detail_head, parent, false);
                return new HeadViewHolder(view);
            case ITEM_FOOT:
                view = mInflater.inflate(R.layout.load_more_data, parent, false);
                mFootViewHolder = new FootViewHolder(view);
                return mFootViewHolder;
            case ITEM_NORMAL:
                view = mInflater.inflate(R.layout.toplist_detail_item, parent, false);
                return new ItemViewHolder(view);
        }
        return null;
    }


    public void startLoading() {
        if (mFootViewHolder != null) {
            mFootViewHolder.tvLoad.setVisibility(View.GONE);
            mFootViewHolder.pbLoading.setVisibility(View.VISIBLE);
        }
    }

    public void stopLoading() {
        if (mFootViewHolder != null) {
            mFootViewHolder.pbLoading.setVisibility(View.GONE);
            mFootViewHolder.tvLoad.setVisibility(View.VISIBLE);
        }
    }


    public void setNoMoreData() {
//        stopLoading();
        mNoMoreData = true;
        stopLoading();
        if (mFootViewHolder!=null){
            mFootViewHolder.tvLoad.setText("貌似没有数据了(；′⌒`)");
        }
    }

    public void setHeadView(String title, String desc) {
        mHeadTitle = title;
        mHeadDesc = desc;
    }

    public void setType(int type) {
        mType = type;
    }

    public void setNoData() {
        mNoData = true;
    }

    class HeadViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_list_title)
        TextView tvListTitle;
        @BindView(R.id.tv_list_desc)
        TextView tvListDesc;

        public HeadViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillView(int position) {
            tvListDesc.setText(mHeadDesc);
            tvListTitle.setText(mHeadTitle);
        }
    }

    @Override
    public int getItemCount() {
        if (mMovies != null && mMovies.size() > 0) {
            return mMovies.size() + 2;
        } else if (mPersons != null && mPersons.size() > 0) {
            return mPersons.size() + 2;
        }
        return super.getItemCount();
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
            if (mNoData){
                tvLoad.setText("该榜单数据不存在!!");
                return;
            }
            if (mNoMoreData) {
                tvLoad.setText("貌似没有数据了(；′⌒`)");
                pbLoading.setVisibility(View.GONE);
            }
        }
    }

    class ItemViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.tv_title_cn)
        TextView tvTitleCn;
        @BindView(R.id.tv_scores)
        TextView tvScores;
        @BindView(R.id.tv_title_en)
        TextView tvTitleEn;
        @BindView(R.id.tv_director)
        TextView tvDirector;
        @BindView(R.id.tv_actor)
        TextView tvActor;
        @BindView(R.id.tv_release_date)
        TextView tvReleaseDate;
        @BindView(R.id.tv_story)
        TextView tvStory;
        @BindView(R.id.tv_type)
        TextView tvType;
        public boolean isExpanded;

        public ItemViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            setupRank(position);
            if (mType == 0 || mType == 1) {
                setupMovies(position);
            } else if (mType == 2) {
                setupPerson(position);
            }

            tvStory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandText();
                }
            });
        }

        private void expandText() {
            if (isExpanded) {
                tvStory.setMaxLines(2);
                isExpanded = !isExpanded;
            } else {
                tvStory.setMaxLines(Integer.MAX_VALUE);
                isExpanded = !isExpanded;
            }
        }

        private void setupRank(int position) {
            int rank = position;
            Drawable shape;
            switch (position){
                case 1:
                    shape = ShapeUtils.getOvalShape(8, 8,
                            mContext.getResources().getColor(R.color.color_toplist_first));
                    break;
                case 2:
                    shape = ShapeUtils.getOvalShape(8, 8,
                            mContext.getResources().getColor(R.color.color_toplist_second));
                    break;
                case 3:
                    shape = ShapeUtils.getOvalShape(8, 8,
                            mContext.getResources().getColor(R.color.color_toplist_third));
                    break;
                default:
                    shape = ShapeUtils.getOvalShape(8, 8,
                            mContext.getResources().getColor(R.color.color_toolist_default));
                    break;
            }
            tvRank.setBackgroundDrawable(shape);
            tvRank.setText(position < 10 ? "0" + rank : rank + "");
        }

        private void setupMovies(int position) {
            TopListDetails.MoviesBean item = (TopListDetails.MoviesBean) getItem(position);
            PicLoadUtils.loadNormalPic(mContext, item.getPosterUrl(), ivCover);
            tvTitleCn.setText(TextUtils.handleEmptyText(item.getName()));
            tvTitleEn.setText(TextUtils.handleEmptyText(item.getNameEn()));
            tvType.setText("类型：" + TextUtils.handleEmptyText(item.getMovieType()));
            tvScores.setText(item.getRating() > 0 ? item.getRating() + "" : "");
            tvDirector.setText("导演：" + TextUtils.handleEmptyText(item.getDirector()));
            tvActor.setText("主演：" + TextUtils.handleEmptyText(item.getActor()));
            tvReleaseDate.setText("上映时间/地区:" +
                    TextUtils.handleEmptyText(item.getReleaseDate()) + "/" +
                    TextUtils.handleEmptyText(item.getReleaseLocation()));
            tvStory.setText("简介："+TextUtils.handleEmptyText(item.getRemark()));
        }

        private void setupPerson(int position) {
            TopListDetails.PersonsBean item = (TopListDetails.PersonsBean) getItem(position);
            PicLoadUtils.loadNormalPic(mContext, item.getPosterUrl(), ivCover);
            tvTitleCn.setText(TextUtils.handleEmptyText(item.getNameCn()));
            tvTitleEn.setText(TextUtils.handleEmptyText(item.getNameEn()));
            tvType.setText("星座：" + TextUtils.handleEmptyText(item.getConstellation()));
            tvDirector.setText("性别：" + TextUtils.handleEmptyText(item.getSex()));
            tvReleaseDate.setText("生日：" + TextUtils.getBirthDay(item.getBirthYear(),item.getBirthDay()));
            tvActor.setText("出生地：" + TextUtils.handleEmptyText(item.getBirthLocation()));
            tvStory.setText(TextUtils.handleEmptyText(item.getSummary()));
            tvScores.setVisibility(View.INVISIBLE);
        }
    }
}
