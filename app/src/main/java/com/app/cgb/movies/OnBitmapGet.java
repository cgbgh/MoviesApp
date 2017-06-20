package com.app.cgb.moviepreview;

import android.graphics.Bitmap;
import android.widget.ImageView;

public interface OnBitmapGet {
    void onGet(Bitmap bitmap, int position, String path);
    void onFail(Exception e);
}
