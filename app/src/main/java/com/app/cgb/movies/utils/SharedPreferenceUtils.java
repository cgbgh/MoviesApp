package com.app.cgb.moviepreview.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.app.cgb.moviepreview.Constants;

/**
 * Created by cgb on 2017/2/22.
 */
public class SharedPreferenceUtils {

    public static int getNavigationItem(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(Constants.NAVIGATION_ITEM,-1);
    }
    public static void putNevigationItem(Context context,int id){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putInt(Constants.NAVIGATION_ITEM,id).commit();
    }
}
