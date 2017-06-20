package com.app.cgb.moviepreview;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.app.cgb.moviepreview.basic.App;
import com.app.cgb.moviepreview.utils.SizeUtils;
import com.squareup.picasso.Transformation;


public class CircleTransform implements Transformation {
    private final int mSize;

    public CircleTransform(int size) {
        mSize = size;
    }

    @Override
    public Bitmap transform(Bitmap source) {

//获取最小边长
        int size;
        int y;
        if (source.getHeight()>source.getWidth()){
            size = source.getWidth();
            y = 0;
        }else {
            size = source.getHeight();
            y = (source.getHeight() - size)/2;
        }
        int x = (source.getWidth() - size) / 2;
        //创建一个正方形区域的Btimap
        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        //创建一张可以操作的正方形图片的位图
        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
        if (squaredBitmap != source) {
            source.recycle();
        }
        // 创建一个画布Canvas
        Canvas canvas = new Canvas(bitmap);
        //创建画笔
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        //圆形半径
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        squaredBitmap.recycle();
        int i = SizeUtils.dp2px(App.getApplication(), mSize);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
        if (bitmap != scaledBitmap){
            bitmap.recycle();
        }
        return scaledBitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
