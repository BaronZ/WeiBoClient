package com.zzb.weibo.http.api;

import com.zzb.weibo.http.base.ApiUrl;
import com.zzb.weibo.model.AccessToken;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 *
 * Created by ZZB on 2015/9/1.
 */
public interface AuthApi {

    @FormUrlEncoded
    @POST(ApiUrl.Auth.ACCESS_TOKEN_URL)
    Observable<AccessToken> getAccessToken(@Field("code") String code);
}
