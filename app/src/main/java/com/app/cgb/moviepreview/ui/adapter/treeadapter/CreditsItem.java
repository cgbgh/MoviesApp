package com.app.cgb.moviepreview.ui.adapter.treeadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.Credits;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.List;

public class CreditsItem extends TreeAdapterItem<Credits.TypesBean.PersonsBean>{

    public CreditsItem(Credits.TypesBean.PersonsBean data,Context context) {
        super(data,context);
    }

    @Override
    protected List<TreeAdapterItem> initChildren(Credits.TypesBean.PersonsBean data) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        final Credits.TypesBean.PersonsBean data = getData();
        PicLoadUtils.loadNormalPic(mContext,data.getImage(), (ImageView) holder.getView(R.id.iv_credit));
        ImageView ivRole = holder.getView(R.id.iv_role);
        if (data.getRoleCover() != null && !data.getRoleCover().isEmpty()){
            ivRole.setVisibility(View.VISIBLE);
            PicLoadUtils.loadNormalPic(mContext,data.getRoleCover(), ivRole);
        }else {
            ivRole.setVisibility(View.GONE);
        }

        holder.setText(R.id.tv_credit_name,TextUtils.handleEmptyText(data.getName()))
                .setText(R.id.tv_credit_name_en, TextUtils.handleEmptyText(data.getNameEn()));

        if (data.getPersonate()!=null && !data.getPersonate().isEmpty()){
            holder.getView(R.id.tv_role).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_role,"饰演角色："+TextUtils.handleEmptyText(data.getPersonate()));
        }else {
            holder.getView(R.id.tv_role).setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.credits_item;
    }

    @Override
    public int getSpanSize() {
        return 0;
    }
}
