package com.app.cgb.moviepreview.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * <pre>
 *     desc  : 时间相关工具类
 * </pre>
 */
public class TimeUtils {

    public static String mills2String(long mills) {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder, Locale.getDefault());
        builder.setLength(0);
        int totalSec = Math.round(mills/1000);
        int sec = totalSec%60;
        int min = (totalSec/60)%60;
        int hour = totalSec/3600;
        if (hour>0){
            return formatter.format("%d:%02d:%02d",hour,min,sec).toString();
        }else {
            return formatter.format("%02d:%02d",min,sec).toString();
        }

    }
    public static String sec2String(int totalSec) {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder, Locale.getDefault());
        builder.setLength(0);
        int sec = totalSec%60;
        int min = (totalSec/60)%60;
        int hour = totalSec/3600;
        if (hour>0){
            return formatter.format("%d:%02d:%02d",hour,min,sec).toString();
        }else {
            return formatter.format("%02d:%02d",min,sec).toString();
        }

    }


    public static String getSystemTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static String getPublishTime(int publishTime) {
        String strPublicTime = "";
        if (publishTime>0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            strPublicTime = dateFormat.format((publishTime - 8 * 60 * 60) * 1000L);
        }
        return strPublicTime;
    }

}