package com.app.cgb.moviepreview.ui.adapter.treeadapter;

import android.content.Context;
import android.widget.ImageView;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.AwardsBean;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;


public class AwardFestivalItem extends TreeAdapterItem<AwardsBean.Awards> {

    private AwardsBean.BaseFestival mFestivals;

    public AwardFestivalItem(AwardsBean.Awards data, Context context) {
        super(data,context);
    }

    @Override
    protected List<TreeAdapterItem> initChildren(AwardsBean.Awards data) {
        List<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        List<? extends AwardsBean.BaseAwards> nominateAwards = data.getNominateAwards();
        List<? extends AwardsBean.BaseAwards> winAwards = data.getWinAwards();
        if (nominateAwards != null && nominateAwards.size() > 0) {
            for (int i = 0; i < nominateAwards.size(); i++) {
                AwardsBean.BaseAwards nominateAwardsBean = nominateAwards.get(i);
                if (i == 0){
                    nominateAwardsBean.setIsTitle(true);
                }
                AwardItem awardItem = new AwardItem(nominateAwardsBean,mContext);
                treeAdapterItems.add(awardItem);
            }
        }
        if (winAwards != null && winAwards.size() > 0){
            for (int i = 0; i < winAwards.size(); i++) {
                AwardsBean.BaseAwards winAwardsBean = winAwards.get(i);
                if (i == 0){
                    winAwardsBean.setIsTitle(true);
                }
                AwardItem awardItem = new AwardItem(winAwardsBean,mContext);
                treeAdapterItems.add(awardItem);
            }
        }
        return treeAdapterItems;
    }

    public void setFestivals(AwardsBean.BaseFestival festivals){
        mFestivals = festivals;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        PicLoadUtils.loadNormalPic(mContext, mFestivals.getImg(),
                (ImageView) holder.getView(R.id.iv_festival));
        holder.setText(R.id.tv_festival,mFestivals.getNameCn())
                .setText(R.id.tv_festival_en, TextUtils.handleEmptyText(mFestivals.getNameEn()))
                .setText(R.id.tv_award_count,getAwardsCount());
    }

    private String getAwardsCount() {
        int nominateCount = getData().getNominateCount();
        int winCount = getData().getWinCount();
        StringBuilder builder = new StringBuilder();
        if (nominateCount == 0) {
            builder.append("共获奖").append(winCount).append("次");
        } else if (winCount == 0) {
            builder.append("共获提名").append(nominateCount).append("次");
        } else {
            builder.append("共获奖").append(winCount).append("次，提名").append(nominateCount).append("次");
        }
        return builder.toString();
    }

    @Override
    public int getLayoutId() {
        return R.layout.awards_festival_item;
    }

    @Override
    public int getSpanSize() {
        return 0;
    }
}
