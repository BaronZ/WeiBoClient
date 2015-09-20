package com.zzb.weibo.http.api;

import com.zzb.weibo.http.base.ApiUrl;
import com.zzb.weibo.model.UID;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by ZZB on 2015/9/20.
 */
public interface UserApi {

    @GET(ApiUrl.User.GET_UID)
    Observable<UID> getUID();
}
