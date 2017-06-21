package com.app.cgb.moviepreview;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;


public class TranslationTransformation implements Transformation {
    private final int mHeight;
    private final int mWidth;

    public TranslationTransformation(int width,int height) {
        mHeight = height;
        mWidth = width;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int newWidth;
        int newHeight;
        int x;
        int y;
        int width = source.getWidth();
        int height = source.getHeight();
        float ratio = (float)mHeight/mWidth;
        if (width*ratio < height){
            newHeight = (int) (width*ratio);
            newWidth = width;
            x= 0;
            y= 0;
        }else {
            newHeight = height;
            newWidth = (int) (height/ratio);
            x =(width-newWidth)/2;
            y=0;
        }
        Bitmap bitmap = Bitmap.createBitmap(source, x, y, newWidth, newHeight);
        if (source!=bitmap){
            source.recycle();
        }
        return bitmap;
    }

    @Override
    public String key() {
        return "translation";
    }
}
