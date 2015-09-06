package com.zzb.weibo.http.base;

import com.zzb.weibo.BuildConfig;
import com.zzb.weibo.MyApplication;
import com.zzb.weibo.data.AccessTokenKeeper;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

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
        mWeiboRestAdapter = new RestAdapter.Builder()
                .setEndpoint(HttpConfig.END_POINT)
                .setRequestInterceptor(tokenInterceptor)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }

    //自定义end point的api
    public static <T> T getApi(String endPoint, Class<T> tClass) {
        return new RestAdapter.Builder().setEndpoint(endPoint).build().create(tClass);
    }

    //默认end point是HttpConfig.END_POINT
    public static <T> T getApi(Class<T> tClass) {
        T api = getWeiboRestAdapterAdapter().create(tClass);
        return api;
    }
}
