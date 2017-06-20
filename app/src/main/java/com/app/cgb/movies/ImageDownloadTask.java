package com.app.cgb.moviepreview;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;

import com.app.cgb.moviepreview.basic.App;
import com.app.cgb.moviepreview.utils.ToastUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.NOTIFICATION_SERVICE;


public class ImageDownloadTask extends AsyncTask<String, Integer, Void> {

    private static final int NOTIFY_ID = 101;
    private final Context mContext;
    private final NotificationManager manager;
    private final String mImgName;
    private Notification.Builder mBuilder;

    public ImageDownloadTask(String imgName) {
        mContext = App.getApplication();
        manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mImgName = imgName;
    }

    @Override
    protected Void doInBackground(String... params) {
        String imageUrl = params[0];
        int lastIndexOf = imageUrl.lastIndexOf("_");
        imageUrl = imageUrl.substring(0, lastIndexOf)+"_1920.jpg";
        dowloadImg(imageUrl);
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onProgressUpdate(Integer... values) {
        mBuilder.setProgress(100, values[0], false);
        manager.notify(NOTIFY_ID, mBuilder.build());
        super.onProgressUpdate(values);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mBuilder = new Notification.Builder(App.getApplication())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("下载进度")
                .setProgress(100, 0, false);
        manager.notify(NOTIFY_ID, mBuilder.build());
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        manager.cancel(NOTIFY_ID);
        ToastUtils.showShortToastSafe(App.getApplication(), "下载成功");
    }

    /**
     * 将图片下载到sd卡
     *
     * @param imgUrl
     */
    private void dowloadImg(String imgUrl) {
        HttpURLConnection con = null;
        FileOutputStream fos;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File imgFile;
        try {
            URL url = new URL(imgUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(8 * 1000);
            con.setReadTimeout(15 * 1000);
//            con.setDoOutput(true);
//            con.setDoInput(true);
            imgFile = new File(getImgPath(imgUrl));
            bis = new BufferedInputStream(con.getInputStream());
            fos = new FileOutputStream(imgFile);
            bos = new BufferedOutputStream(fos);
            byte[] b = new byte[1024];
            int len;
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private String getImgPath(String imgUrl) {
//        int lastIndex = imgUrl.lastIndexOf("/");
//        String imgName = imgUrl.substring(lastIndex + 1);
        String imgDir = Environment.getExternalStorageDirectory().getPath() + "/movieImages/";
        File file = new File(imgDir);
        if (!file.exists()) {
            file.mkdirs();
        }
//        String imgPath = imgDir + imgName;
        return imgDir + mImgName+".jpg";
    }
}
