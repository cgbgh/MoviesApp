package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;

import com.app.cgb.moviepreview.entity.NewsDetail;

import java.util.List;

public class NewsImgPageAdapter extends ImgPageAdapter {

    public NewsImgPageAdapter(Context context, List imagesList) {
        super(context, imagesList);
    }

    @Override
    public String getImgUrl(int position) {
        Object img = mImages.get(position);
        if (img instanceof NewsDetail.ImagesBean){
            return ((NewsDetail.ImagesBean) img).getUrl1();
        }else if (img instanceof String){
            return (String) img;
        }
        return "";
    }
}
