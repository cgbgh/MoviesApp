package com.app.cgb.moviepreview.api;


import com.app.cgb.moviepreview.CacheInterceptor;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.basic.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiManager {
    private static final long DEFAULT_TIMEOUT = 12;
    private static final int ONE_DAY = 24 * 3600;
    private static final int HALF_HOUR = 30;
    private static ApiManager sApiManager;
    private MTimeMovieApi mTimeMovieApi;
    private MTimeTicketApi mTimeTicketApi;
    private MTimeMovieApi mTimeRefreshApi;
    private static final Object object = new Object();


    private OkHttpClient genericClient(int maxAge) {
        File topListCache = new File(App.getApplication().getCacheDir().getAbsolutePath(), "TopListCache");
        if (!topListCache.exists()){
            topListCache.mkdir();
        }
        return getClient(topListCache,1024*1024*10,DEFAULT_TIMEOUT,maxAge);
    }

    private static OkHttpClient getClient(File cacheDir, long size, long timeout,int maxAge) {
        Cache cache = new Cache(cacheDir, size);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheInterceptor(maxAge))
                .addInterceptor(new CacheInterceptor(maxAge))
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    private ApiManager() {}

    public static ApiManager getInstance() {
        if (sApiManager == null) {
            synchronized (ApiManager.class) {
                if (sApiManager == null) {
                    sApiManager = new ApiManager();
                }
            }
        }
        return sApiManager;
    }

    public MTimeMovieApi getMTimeMovieApi() {
        if (mTimeMovieApi == null) {
            synchronized (this) {
                if (mTimeMovieApi == null) {
                    mTimeMovieApi = new Retrofit.Builder()
                            .client(genericClient(ONE_DAY))
                            .baseUrl(Constants.MTIME_BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(MTimeMovieApi.class);
                }

            }
        }
        return mTimeMovieApi;
    }

    public MTimeTicketApi getMTimeTicketApi() {
        if (mTimeTicketApi == null) {
            synchronized (object) {
                if (mTimeTicketApi == null) {
                    mTimeTicketApi = new Retrofit.Builder()
                            .client(genericClient(ONE_DAY))
                            .baseUrl(Constants.MTIME_TICKET_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(MTimeTicketApi.class);
                }

            }
        }
        return mTimeTicketApi;
    }

    public MTimeMovieApi getmTimeRefreshApi() {
        if (mTimeRefreshApi == null) {
            synchronized (this) {
                if (mTimeRefreshApi == null) {
                    mTimeRefreshApi = new Retrofit.Builder()
                            .client(genericClient(HALF_HOUR))
                            .baseUrl(Constants.MTIME_BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(MTimeMovieApi.class);
                }

            }
        }
        return mTimeRefreshApi;
    }

}
