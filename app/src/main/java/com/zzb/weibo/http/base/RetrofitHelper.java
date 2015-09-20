package com.zzb.weibo.http.base;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.zzb.weibo.BuildConfig;
import com.zzb.weibo.MyApplication;
import com.zzb.weibo.data.AccessTokenKeeper;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * RetrofitHelper用来生成retrofit api
 * Created by ZZB on 2015/9/1.
 */
public class RetrofitHelper {
    private static RestAdapter mWeiboRestAdapter;

    private static RestAdapter getWeiboRestAdapterAdapter() {
        if (mWeiboRestAdapter == null) {
            initWeiBoRestAdapter();
        }
        return mWeiboRestAdapter;
    }

    private static void initWeiBoRestAdapter() {
        RequestInterceptor tokenInterceptor = request -> {
            String token = AccessTokenKeeper.getAccessToken(MyApplication.APP_CONTEXT);
            request.addHeader("Authorization", "OAuth2 " + token);
        };
        mWeiboRestAdapter = getBaseRestAdapterBuilder(HttpConfig.END_POINT)
                .setRequestInterceptor(tokenInterceptor)
                .build();
    }
    private static RestAdapter.Builder getBaseRestAdapterBuilder(String endPoint){
        OkHttpClient okHttpClient = new OkHttpClient();
        if(BuildConfig.DEBUG){
            okHttpClient.networkInterceptors().add(new StethoInterceptor());
        }
        okHttpClient.setConnectTimeout(HttpConfig.CONNECTION_TIMEOUT_IN_SECOND, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(HttpConfig.READ_TIMEOUT_IN_SECOND, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(HttpConfig.WRITE_TIMEOUT_IN_SECOND, TimeUnit.SECONDS);
        return new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
    }
    //自定义end point的api
    public static <T> T getApi(String endPoint, Class<T> tClass) {
        return getBaseRestAdapterBuilder(endPoint).build().create(tClass);
    }

    //默认end point是HttpConfig.END_POINT
    public static <T> T getApi(Class<T> tClass) {
        T api = getWeiboRestAdapterAdapter().create(tClass);
        return api;
    }
}
