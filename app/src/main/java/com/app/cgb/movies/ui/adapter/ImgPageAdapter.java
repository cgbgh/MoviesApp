package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.cgb.moviepreview.utils.PicLoadUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class ImgPageAdapter<T> extends PagerAdapter{

    public List<T> mImages = new ArrayList<>();
    private final Context mContext;
    private SparseArray<ImageView> ivList = new SparseArray<>();

    public ImgPageAdapter(Context context, List<T> imagesList) {
        mImages = imagesList;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img = ivList.get(position);
        if (img == null){
            img = new ImageView(mContext);
            img.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ivList.put(position,img);
        }
        String imgUrl = getImgUrl(position);
        PicLoadUtils.loadFitPic(mContext, imgUrl,img);
        container.addView(img);
        return img;
    }

    public abstract String getImgUrl(int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
