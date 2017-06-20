package com.app.cgb.moviepreview.basic;

import android.app.Application;
import android.content.Context;


public class App extends Application {

    private static App mApplication;

    public static Context getApplication() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

//    private Bitmap screenShot(BaseActivity activity) {
//        View view = activity.getWindow().getDecorView();
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap b1 = view.getDrawingCache();
//
//        //获取状态栏高度
//        Rect frame = new Rect();
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;
//
//        //获取屏幕长和高
//        DisplayMetrics dm = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        Bitmap bitmap = Bitmap.createBitmap(b1, 0, statusBarHeight,
//                width, height - statusBarHeight);
//        view.destroyDrawingCache();
//        b1.recycle();
//        return bitmap;
//    }

}
