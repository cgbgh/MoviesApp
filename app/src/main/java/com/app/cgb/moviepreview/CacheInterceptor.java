package com.app.cgb.moviepreview;

import com.app.cgb.moviepreview.basic.App;
import com.app.cgb.moviepreview.utils.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CacheInterceptor implements Interceptor {

    private final int mMaxAge;

    public CacheInterceptor(int maxAge){
        mMaxAge = maxAge;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkUtil.isNetWorkAvailable(App.getApplication())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetWorkUtil.isNetWorkAvailable(App.getApplication())) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(mMaxAge, TimeUnit.SECONDS)
                    .build();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxStale = 30;
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(maxStale, TimeUnit.DAYS)
                    .build();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
