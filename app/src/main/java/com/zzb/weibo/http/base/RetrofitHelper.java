package com.zzb.weibo.http.base;

import retrofit.RestAdapter;

/**
 * Created by ZZB on 2015/9/1.
 */
public class RetrofitHelper {
    private static RestAdapter mWeiboRestAdapter = new RestAdapter.Builder().setEndpoint(HttpConfig.END_POINT).build();

    private static RestAdapter getWeiboRestAdapterAdapter(){
        if(mWeiboRestAdapter == null){
            mWeiboRestAdapter = new RestAdapter.Builder().setEndpoint(HttpConfig.END_POINT).build();
        }
        return mWeiboRestAdapter;
    }

    public static <T> T getApi(Class<T> tClass){
        T api = getWeiboRestAdapterAdapter().create(tClass);
        return api;
    }
}
