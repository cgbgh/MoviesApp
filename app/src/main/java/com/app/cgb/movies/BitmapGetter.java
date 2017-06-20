package com.app.cgb.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.squareup.picasso.Picasso;

import java.io.IOException;


public class BitmapGetter {
    private OnBitmapGet mOnBitmapGet;
    private final Context mContext;
    private int mPosition;
    private String mPath;

    public BitmapGetter(Context context) {
        mContext = context;
    }

    public void getBitmap(String path, int position) {
        GetBitmapTask task = new GetBitmapTask();
        task.execute(path);
        mPosition = position;
        mPath = path;
    }

    public void setOnBItmapGet(OnBitmapGet onBItmapGet) {
        mOnBitmapGet = onBItmapGet;
    }

    class GetBitmapTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            String path = params[0];
            try {
                bitmap = Picasso.with(mContext)
                        .load(path)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
                if (mOnBitmapGet != null) {
                    mOnBitmapGet.onFail(e);
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (mOnBitmapGet != null) {
                if (bitmap != null) {
                    mOnBitmapGet.onGet(bitmap, mPosition, mPath);
                }
            }
        }

    }

}
