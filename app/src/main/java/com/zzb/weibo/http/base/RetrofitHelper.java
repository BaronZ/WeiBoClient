package com.zzb.weibo.http.base;

import com.zzb.weibo.MyApplication;
import com.zzb.weibo.data.AccessTokenKeeper;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * RetrofitHelper用来生成retrofit api
 * Created by ZZB on 2015/9/1.
 */
public class RetrofitHelper {
    private static RestAdapter mWeiboRestAdapter = new RestAdapter.Builder().setEndpoint(HttpConfig.END_POINT).build();

    private static RestAdapter getWeiboRestAdapterAdapter(){
        if(mWeiboRestAdapter == null){
            RequestInterceptor tokenInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestInterceptor.RequestFacade request) {
                    String token = AccessTokenKeeper.readAccessToken(MyApplication.APP_CONTEXT).token;
                    request.addHeader("access_token", token);
                }
            };
            mWeiboRestAdapter = new RestAdapter.Builder().setEndpoint(HttpConfig.END_POINT)
//                    .setRequestInterceptor(tokenInterceptor)
                    .build();

        }
        return mWeiboRestAdapter;
    }
    //自定义end point的api
    public static <T> T getApi(String endPoint, Class<T> tClass){
        return new RestAdapter.Builder().setEndpoint(endPoint).build().create(tClass);
    }
    //默认end point是HttpConfig.END_POINT
    public static <T> T getApi(Class<T> tClass){
        T api = getWeiboRestAdapterAdapter().create(tClass);
        return api;
    }
}
