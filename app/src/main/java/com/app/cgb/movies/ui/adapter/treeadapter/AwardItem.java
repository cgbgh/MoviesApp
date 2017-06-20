package com.app.cgb.moviepreview.ui.adapter.treeadapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.AwardsBean;
import com.app.cgb.moviepreview.entity.MovieBasicDetail.DataBean.BasicBean.AwardBean.AwardListBean;
import com.app.cgb.moviepreview.entity.PersonsDetail.DataBean.BackgroundBean;
import com.app.cgb.moviepreview.ui.activity.MTMovieDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import java.util.List;


public class AwardItem extends TreeAdapterItem<AwardsBean.BaseAwards> {

    public AwardItem(AwardsBean.BaseAwards data,Context context) {
        super(data,context);
    }

    @Override
    protected List<TreeAdapterItem> initChildren(AwardsBean.BaseAwards data) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        AwardsBean.BaseAwards data = getData();
        if (data.getPersons() != null){
            setupMovieAwardItem(holder,data);
        }else {
            setupPersonAwardItem(holder,data);
        }
    }

    private void setupPersonAwardItem(ViewHolder holder, final AwardsBean.BaseAwards data) {
        String awardType = "";
        if (data instanceof BackgroundBean.AwardsBean.WinAwardsBean && data.isTitle()){
            awardType = "获奖";
            holder.setText(R.id.tv_award_type,awardType);
        }else if (data instanceof BackgroundBean.AwardsBean.NominateAwardsBean && data.isTitle()){
            awardType = "提名";
            holder.setText(R.id.tv_award_type,awardType);
        }

        holder.setText(R.id.tv_award_name,getAwardName(data));

        View movieRoot = holder.getView(R.id.ll_movies);
        if (data.getMovieId() != 0){
            movieRoot.setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_award_movie,awardType + "影片:"+data.getMovieTitle())
                    .setText(R.id.tv_role,"饰演角色:"+data.getRoleName())
                    .setOnClickListener(R.id.ll_movies, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startMovieDetailActivity(data);
                        }
                    });
            PicLoadUtils.loadNormalPic(mContext,data.getImage(), (ImageView) holder.getView(R.id.iv_awards));
        }
    }

    private void startMovieDetailActivity(AwardsBean.BaseAwards data) {
        int movieId = data.getMovieId();
        Intent intent = new Intent(mContext, MTMovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID,movieId);
        mContext.startActivity(intent);
    }

    private String getAwardName(AwardsBean.BaseAwards data) {
        int sequenceNumber = data.getSequenceNumber();
        String festivalEventYear = data.getFestivalEventYear();
        String awardName = data.getAwardName();
        return "第"+sequenceNumber +"届("+festivalEventYear+ ") - " + awardName;
    }

    private void setupMovieAwardItem(ViewHolder holder, AwardsBean.BaseAwards data) {
        if (data instanceof AwardListBean.WinAwardsBean && data.isTitle()){
            holder.setText(R.id.tv_award_type,"获奖");
        }else if (data instanceof AwardListBean.NominateAwardsBean && data.isTitle()){
            holder.setText(R.id.tv_award_type,"提名");
        }
        int sequenceNumber = data.getSequenceNumber();
        String festivalEventYear = data.getFestivalEventYear();
        String sequenceText = "第"+sequenceNumber +"届("+festivalEventYear+ ") - ";
        holder.setText(R.id.tv_sequence,sequenceText);
        holder.setText(R.id.tv_award_name,data.getAwardName());
        String persons = getPersons(data);

        if (!persons.isEmpty()){
            holder.setText(R.id.tv_persons, persons);
        }
    }

    private String getPersons(AwardsBean.BaseAwards data) {
        List<? extends AwardsBean.BasePerson> persons = data.getPersons();
        String personText = "";
        if (persons != null && persons.size() > 0){
            for (int i = 0; i < persons.size(); i++) {
                if (i != 0){
                    personText += "\n";
                }
                String nameCn = persons.get(i).getNameCn();
                if (nameCn != null && !nameCn.isEmpty()){
                    personText += nameCn;
                }else {
                    String nameEn = persons.get(i).getNameEn();
                    personText += nameEn;
                }
            }
        }
        return personText;
    }

    @Override
    public int getLayoutId() {
        AwardsBean.BaseAwards data = getData();
        if (data.getPersons() != null){
            if (data.isTitle()){
                return R.layout.movie_awards_item_title;
            }
            return R.layout.movie_awards_item;
        }else if (data.isTitle()){
            return R.layout.person_awards_item_title;
        }
        return R.layout.person_awards_item;
    }

    @Override
    public int getSpanSize() {
        return 0;
    }
}
