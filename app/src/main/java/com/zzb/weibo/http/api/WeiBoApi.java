package com.zzb.weibo.http.api;

import com.zzb.weibo.model.StatusList;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * 微博api
 * Created by ZZB on 2015/9/6.
 */
public interface WeiBoApi {


    @GET("/statuses/friends_timeline.json")
    Observable<StatusList> getFriendsTimeLine(@Query("access_token") String token);
}
