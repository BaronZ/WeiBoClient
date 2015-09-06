package com.zzb.weibo.http.api;

import com.zzb.weibo.http.base.ApiUrl;
import com.zzb.weibo.model.StatusList;

import retrofit.http.GET;
import rx.Observable;

/**
 * 微博api
 * Created by ZZB on 2015/9/6.
 */
public interface WeiBoApi {


    /**
     * 获取当前登录用户及其所关注用户的最新微博
     *@author ZZB
     *created at 2015/9/6 14:26
     */
    @GET(ApiUrl.WeiBo.FRIENDS_TIMELINE)
    Observable<StatusList> getFriendsTimeLine();
}
