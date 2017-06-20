package com.app.cgb.moviepreview.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;

import com.app.cgb.moviepreview.CacheInterceptor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by cgb on 2017/2/20.
 */
public class NetWorkUtil {
    private static long lastByte;
    private static long lastTime;

    private NetWorkUtil() {
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 检测wifi是否连接
     *
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 检测3G是否连接
     *
     * @return
     */
    public static boolean is3gConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static long getNetSpeed(Context context) {
        long uidRxBytes = TrafficStats.getUidRxBytes(context.getApplicationInfo().uid);
        long nowTime = System.currentTimeMillis();
        long nowByte = uidRxBytes == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);
        long speed = (nowByte - lastByte) * 1000 / (nowTime - lastTime);
        lastByte = nowByte;
        lastTime = nowTime;
        return speed;
    }

    public static boolean isNetVideo(String uri) {
        boolean result = false;
        if (uri.toLowerCase().startsWith("http")
                || uri.toLowerCase().startsWith("rtsp")
                || uri.toLowerCase().startsWith("mms")) {

            result = true;

        }
        return result;
    }

    public static OkHttpClient getClient(File cacheDir, long size, long timeout,int maxAge) {
        Cache cache = new Cache(cacheDir, size);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheInterceptor(maxAge))
                .addInterceptor(new CacheInterceptor(maxAge))
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .cache(cache)
                .build();
        return httpClient;
    }

    public static String getFormatedHtml(String content) {
        Document doc = Jsoup.parse(content);
        Elements imgs = doc.getElementsByTag("img");
        int i = 0;
        for (Element img : imgs) {
            img.attr("width", "100%").attr("height", "auto");
            img.attr("onclick","window.android.showPic("+i+")");
            i++;
        }
        Elements videos = doc.getElementsByTag("video");
        for (Element video : videos) {
            video.attr("width","100%").attr("height","auto");
        }
        return doc.toString();

    }
}
