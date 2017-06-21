package com.app.cgb.moviepreview.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

import com.app.cgb.moviepreview.CircleTransform;
import com.app.cgb.moviepreview.ImageDownloadTask;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.TranslationTransformation;
import com.squareup.picasso.Picasso;


/**
 * Created by cgb on 2017/2/28.
 */

public class PicLoadUtils {

    public static void loadCirclePic(Context context, String path, ImageView imageView, int size) {
        if (path!=null && !path.isEmpty()) {
            Picasso picasso = Picasso.with(context.getApplicationContext());
            picasso.load(path)
                    .transform(new CircleTransform(size))
                    .config(Bitmap.Config.ARGB_4444)
                    .into(imageView);
        }else {
            loadErrorCirclePic(context,imageView,size);
        }
    }

    private static void loadErrorCirclePic(Context context, ImageView imageView, int size) {
        Picasso picasso = Picasso.with(context.getApplicationContext());
        picasso.load(R.drawable.no_poster)
                .transform(new CircleTransform(size))
                .config(Bitmap.Config.ARGB_4444)
                .into(imageView);
    }

    public static void loadNormalPic(Context context, String path, ImageView imageView) {
        Picasso picasso = Picasso.with(context.getApplicationContext());
        if (path!=null && !path.isEmpty()){

            picasso.load(path)
                    .config(Bitmap.Config.RGB_565)
                    .fit()
                    .centerCrop()
                    .placeholder(R.color.bg_dark_grey)
                    .error(R.drawable.no_poster)
                    .into(imageView);
        }else {
            loadErrorPic(context,imageView);
        }
    }
    public static void loadCirclePic(Context context, String path, ImageView imageView) {
        Picasso picasso = Picasso.with(context.getApplicationContext());
        if (path!=null && !path.isEmpty()){

            picasso.load(path)
                    .config(Bitmap.Config.RGB_565)
                    .fit()
                    .centerInside()
                    .placeholder(R.color.bg_dark_grey)
                    .error(R.drawable.no_poster)
                    .into(imageView);
        }else {
            loadErrorPic(context,imageView);
        }
    }
    public static void loadFitPic(Context context, String path, ImageView imageView) {
        Picasso picasso = Picasso.with(context.getApplicationContext());
        picasso.load(path)
                .config(Bitmap.Config.RGB_565)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.darker_gray)
                .into(imageView);
    }

    public static void loadTranslationPic(Context context, String path, ImageView imageView,int width,int height) {
        Picasso picasso = Picasso.with(context.getApplicationContext());
        TranslationTransformation transformation = new TranslationTransformation(width, height);
        if (path!= null && !path.isEmpty()){
            picasso.load(path)
                    .config(Bitmap.Config.RGB_565)
                    .transform(transformation)
                    .error(R.drawable.no_poster)
                    .placeholder(R.color.bg_dark_grey)
                    .into(imageView);
        }else {
            loadErrorPic(context,imageView);
        }
    }

    public static void loadErrorPic(Context context, ImageView imageView) {
        Picasso.with(context.getApplicationContext())
                .load(R.drawable.no_poster)
                .into(imageView);
    }

    public static void loadNewCirclePic(Context context, String path, ImageView imageView, int size) {
        if (path!=null && !path.isEmpty()) {
            Picasso picasso = new Picasso.Builder(context.getApplicationContext()).build();
            picasso.load(path)
                    .transform(new CircleTransform(size))
                    .config(Bitmap.Config.ARGB_4444)
                    .into(imageView);
        }else {
            loadErrorCirclePic(context,imageView,size);
        }
    }

    public static void loadPlacePic(Context context, ImageView imageView) {
        Picasso.with(context.getApplicationContext())
                .load(R.drawable.bg_grey)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    public static void loadResizePic(Context context, String path, ImageView imageView, int width, int height) {
        Picasso picasso = Picasso.with(context.getApplicationContext());

        if (path!=null && !path.isEmpty()){

            picasso.load(path)
                    .config(Bitmap.Config.RGB_565)
                    .resize(width,height)
                    .centerCrop()
                    .placeholder(R.color.bg_dark_grey)
                    .error(R.drawable.no_poster)
                    .into(imageView);
        }else {
            loadErrorPic(context,imageView);
        }
    }


    public static void downloadPic(Context context , String imgUrl, String name){
        if (hasSDCard()) {
            if (NetWorkUtil.isNetWorkAvailable(context)) {
                    ToastUtils.showShortToastSafe(context, "图片下载中...");
                    ImageDownloadTask task = new ImageDownloadTask(name);
                    task.execute(imgUrl);
            } else {
                ToastUtils.showShortToastSafe(context, "当前网络不可用...");
            }
        } else {
            ToastUtils.showShortToastSafe(context, "未发现SD卡...");
        }
    }

    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

}
