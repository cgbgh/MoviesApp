package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieExtendDetail;
import com.app.cgb.moviepreview.entity.NewsDetail;
import com.app.cgb.moviepreview.entity.PersonsRelation;
import com.app.cgb.moviepreview.ui.activity.MTMovieDetailActivity;
import com.app.cgb.moviepreview.ui.activity.PersonDetailActivity;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import butterknife.BindView;

public class RelatedAdapter extends BaseSingleTypeAdapter implements BaseSingleTypeAdapter.ItemClickListener {

    private final int mType;
    public static final int MOVIE_RELATIONS = 0;
    public static final int PERSON_RELATIONS = 1;
    public static final int NEWS_RELATIONS = 2;

    public RelatedAdapter(Context context, int type) {
        super(context);
        mType = type;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.related_item, parent, false);
        setOnItemClickListener(this);
        return new RelatedViewHolder(view);
    }

    @Override
    public void onItemClick(int position) {
        if (mType == MOVIE_RELATIONS) {
            MovieExtendDetail.RelelatedMovielistBean.MoviesBean moviesBean =
                    (MovieExtendDetail.RelelatedMovielistBean.MoviesBean) getItem(position);
            startActivity(MTMovieDetailActivity.class,Constants.MOVIE_ID,moviesBean.getMovieID());
        }else if (mType == PERSON_RELATIONS){
            PersonsRelation personRelation = (PersonsRelation) getItem(position);
            startActivity(MTMovieDetailActivity.class,Constants.MOVIE_ID,personRelation.getId());
        }else if (mType == NEWS_RELATIONS){
            NewsDetail.RelationsBean newsRelation = (NewsDetail.RelationsBean) getItem(position);
            Class activity = null;
            String key = "";
            if (newsRelation.getType() == 0 || newsRelation.getType() == 1){
                activity = MTMovieDetailActivity.class;
                key = Constants.MOVIE_ID;
            }else if (newsRelation.getType() ==2 ){
                activity = PersonDetailActivity.class;
                key = Constants.PERSON_ID;
            }
            startActivity(activity,key,newsRelation.getId());
        }
    }

    private void startActivity(Class activtiy,String key,int extras) {
        Intent intent = new Intent(mContext, activtiy);
        intent.putExtra(key,extras);
        mContext.startActivity(intent);
    }

    @Override
    public void onHeadClick() {

    }

    @Override
    public void onFootClick() {

    }

    class RelatedViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_related_cover)
        ImageView ivRelatedCover;
//        @BindView(R.id.tv_related_score)
//        TextView tvRelatedScore;
        @BindView(R.id.tv_related_title)
        TextView tvRelatedTitle;

        public RelatedViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            switch (mType) {
                case MOVIE_RELATIONS:
                    MovieExtendDetail.RelelatedMovielistBean.MoviesBean moviesBean =
                            (MovieExtendDetail.RelelatedMovielistBean.MoviesBean) getItem(position);
                    String img = moviesBean.getImg();
                    PicLoadUtils.loadNormalPic(mContext, img, ivRelatedCover);
                    tvRelatedTitle.setText(moviesBean.getTitle());
                    break;
                case PERSON_RELATIONS:
                    PersonsRelation personRelation = (PersonsRelation) getItem(position);
                    PicLoadUtils.loadNormalPic(mContext, personRelation.getImage(), ivRelatedCover);
                    tvRelatedTitle.setText(personRelation.getName());
                    break;
                case NEWS_RELATIONS:
                    NewsDetail.RelationsBean newsRelations = (NewsDetail.RelationsBean) getItem(position);
                    PicLoadUtils.loadNormalPic(mContext,newsRelations.getImage(),ivRelatedCover);
                    tvRelatedTitle.setText(newsRelations.getName());
                    break;
            }

        }
    }
}
