package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;

import com.app.cgb.moviepreview.entity.Images;

import java.util.List;

public class ImgDetaiPageAdapter extends ImgPageAdapter<Images.ImagesBean> {

    private List<Integer> mPositionList;

    public ImgDetaiPageAdapter(Context context, List<Images.ImagesBean> imagesList) {
        super(context, imagesList);
    }

    @Override
    public int getCount() {
        if (mImages!=null && mImages.size()>0){

            if (mPositionList!=null){
                return mPositionList.size();
            }
            return mImages.size();
        }
        return 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public String getImgUrl(int position) {
        int realPosition = position;
        if (mPositionList!=null){
            realPosition = mPositionList.get(position);
        }
        return mImages.get(realPosition).getImage();
    }

    public void setData(List<Images.ImagesBean> imageList) {
        mImages = imageList;
        notifyDataSetChanged();
    }

    public void setList(List<Integer> positionList) {
        mPositionList = positionList;
        notifyDataSetChanged();
    }
}
