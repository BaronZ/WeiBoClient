package com.zzb.weibo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.concurrent.TimeUnit;

/**
 * 微博AccessToken
 * Created by ZZB on 2015/9/1.
 */
public class AccessToken {
    @Expose
    @SerializedName("access_token")
    public String token;//用于调用access_token，接口获取授权后的access token
    @Expose
    @SerializedName("expires_in")
    private long expiresIn;//access_token的生命周期，单位是秒数
    public long expiresTime;//具体过期时间点
    public void setExpiresIn(long expiresIn){
        this.expiresIn = expiresIn;
        expiresTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(expiresIn);
    }
    //是否有效
    public boolean isValid(){
        return System.currentTimeMillis() < expiresTime;
    }
    public long getExpiresIn(){
        return expiresIn;
    }
}
