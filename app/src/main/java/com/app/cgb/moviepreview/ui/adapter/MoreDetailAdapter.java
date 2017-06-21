package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieExtendDetail;
import com.app.cgb.moviepreview.ui.activity.CompanyListActivity;
import com.app.cgb.moviepreview.ui.activity.MediaReviewActivity;
import com.app.cgb.moviepreview.ui.activity.WebTextActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreDetailAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final List<Integer> mList = new ArrayList<>();
    private final int mMovieId;
    private MovieExtendDetail.DataBankEntryBean mData;
    private final int CLASSIC_LINES = 0;
    private final int BEHIND = 1;
    private final int COMPANY = 2;
    private final int MEDIA_REVIEW = 3;


    public MoreDetailAdapter(Context context,int movieId) {
        mContext = context;
        mMovieId = movieId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.more_detail_item, parent, false);
        return new MoreDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        MoreDetailViewHolder viewHolder = (MoreDetailViewHolder) holder;
        viewHolder.fillView(position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mList.get(pos)){
                    case CLASSIC_LINES:
                        startWebTextActivty(WebTextActivity.FROM_CLASSICLINES);
                        break;
                    case BEHIND:
                        startWebTextActivty(WebTextActivity.FROM_BEHINDMAKE);
                        break;
                    case COMPANY:
                        Intent companyIntent = new Intent(mContext, CompanyListActivity.class);
                        companyIntent.putExtra(Constants.MOVIE_ID,mMovieId);
                        mContext.startActivity(companyIntent);
                        break;
                    case MEDIA_REVIEW:
                        Intent mediaIntent = new Intent(mContext, MediaReviewActivity.class);
                        mediaIntent.putExtra(Constants.MOVIE_ID,mMovieId);
                        mContext.startActivity(mediaIntent);
                        break;
                }

            }
        });
    }

    private void startWebTextActivty(int from) {
        Intent intent = new Intent(mContext, WebTextActivity.class);
        intent.putExtra(Constants.MOVIE_ID,mMovieId);
        intent.putExtra(Constants.WEB_TEXT_FROM,from);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(MovieExtendDetail.DataBankEntryBean dataBankEntry) {
        mData = dataBankEntry;
        boolean isClassicLines = mData.isIsClassicLines();
        boolean isCompany = mData.isIsCompany();
        boolean isBehind = mData.isIsBehind();
        boolean isMediaReview = mData.isIsMediaReview();
        if (mList!=null){
            mList.clear();
            if (isClassicLines) mList.add(CLASSIC_LINES);
            if (isBehind) mList.add(BEHIND);
            if (isCompany) mList.add(COMPANY);
            if (isMediaReview) mList.add(MEDIA_REVIEW);
        }
        notifyDataSetChanged();
    }

    class MoreDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_more_title)
        TextView tvMoreTitle;
        @BindView(R.id.tv_more_desc)
        TextView tvMoreDesc;
        public MoreDetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        public void fillView(int position){
            switch (mList.get(position)){
                case CLASSIC_LINES:
                    ivIcon.setImageResource(R.drawable.relation_icon_03);
                    tvMoreTitle.setText("经典台词");
                    tvMoreDesc.setText(mData.getClassicLinesCount()+"段经典台词");
                    break;
                case BEHIND:
                    ivIcon.setImageResource(R.drawable.relation_icon_05);
                    tvMoreDesc.setText("制作幕后");
                    tvMoreTitle.setText("制作幕后");
                    break;
                case COMPANY:
                    ivIcon.setImageResource(R.drawable.relation_icon_04);
                    tvMoreDesc.setText(mData.getCompanyCount()+"家公司参与制作");
                    tvMoreTitle.setText("制作发行");
                    break;
                case MEDIA_REVIEW:
                    ivIcon.setImageResource(R.drawable.relation_icon_02);
                    tvMoreDesc.setText(mData.getMediaReviewCount()+"家媒体相关报道");
                    tvMoreTitle.setText("媒体评论");
                    break;
            }
        }
    }
}
