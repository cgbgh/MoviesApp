package com.app.cgb.moviepreview;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;


import com.app.cgb.moviepreview.basic.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class AcBitmapLoader {

    private static LruCache<String, Bitmap> mMemoryCache;

    private static AcBitmapLoader mImageLoader;
    private final String cachePath;

    private AcBitmapLoader() {
        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        File file = new File(App.getApp().getCacheDir().getAbsoluteFile(), "AcBitmap");
        if (!file.exists()) file.mkdir();
        cachePath = file.getPath();
        // 设置图片缓存大小为程序最大可用内存的1/8
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

    public String getPath(String name){
        return cachePath + "/" + name;
    }

    public static AcBitmapLoader getInstance() {
        if (mImageLoader == null) {
            mImageLoader = new AcBitmapLoader();
        }
        return mImageLoader;
    }

    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key    LruCache的键，这里传入图片的URL地址。
     * @param bitmap LruCache的键，这里传入从网络上下载的Bitmap对象。
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param name
     * @return 对应传入键的Bitmap对象，或者null。
     */
    public Bitmap getBitmapFromMemoryCache(String name) {
        String key = getPath(name);
        return mMemoryCache.get(key);
    }

//    public static int calculateInSampleSize(BitmapFactory.Options options,
//                                            int reqWidth) {
//        // 源图片的宽度
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//        if (width > reqWidth) {
//            // 计算出实际宽度和目标宽度的比率
//            final int widthRatio = Math.round((float) width / (float) reqWidth);
//            inSampleSize = widthRatio;
//        }
//        return inSampleSize;
//    }

//    public static Bitmap decodeSampledBitmapFromResource(String pathName,
//                                                         int reqWidth) {
//        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(pathName, options);
//        // 调用上面定义的方法计算inSampleSize值
//        options.inSampleSize = calculateInSampleSize(options, reqWidth);
//        // 使用获取到的inSampleSize值再次解析图片
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeFile(pathName, options);
//    }

    public void saveBitmap(String name, Bitmap bitmap) {
        addBitmapToMemoryCache(getPath(name),bitmap);
        BitmapSaveTask task = new BitmapSaveTask();
        task.setPath(name);
        task.execute(bitmap);
    }


    class BitmapSaveTask extends AsyncTask<Bitmap, Void, Void> {

        private String mName;

        @Override
        protected Void doInBackground(Bitmap... params) {
            Bitmap bitmap = params[0];
            saveBitmap(bitmap);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        private String saveBitmap(Bitmap bitmap) {
            File file = new File(cachePath + "/" + mName);
            try {
//                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
//                bitmap.recycle();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void setPath(String name) {
            mName = name;
        }
    }

}
