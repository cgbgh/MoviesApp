package com.app.cgb.moviepreview;

import android.graphics.Bitmap;

public interface OnBitmapGet {
    void onGet(Bitmap bitmap, int position, String path);
    void onFail(Exception e);
}
