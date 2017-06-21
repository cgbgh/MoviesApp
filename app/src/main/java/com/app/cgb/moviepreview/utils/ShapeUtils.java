package com.app.cgb.moviepreview.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/**
 * 形状图形Shape相关的工具类
 */

public class ShapeUtils {

    public static Drawable getOvalShape(int width,int height,int color){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setSize(width,height);
        drawable.setColor(color);
        return drawable;
    }
    public static Drawable getRectShape(int width,int height,int color){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setSize(width,height);
        drawable.setColor(color);
        return drawable;
    }

}
