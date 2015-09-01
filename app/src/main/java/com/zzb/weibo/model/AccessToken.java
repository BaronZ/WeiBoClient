package com.zzb.weibo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 微博AccessToken
 * Created by ZZB on 2015/9/1.
 */
public class AccessToken {
    @Expose
    @SerializedName("access_token")
    public String token;
    @Expose
    @SerializedName("expires_in")
    private long expiresIn;
    public long expiresTime;//具体过期时间点
    public void setExpiresIn(long expiresIn){
        this.expiresIn = expiresIn;
        expiresTime = System.currentTimeMillis() + expiresIn;
    }
    //是否有效
    public boolean isValid(){
        return System.currentTimeMillis() < expiresTime;
    }
}
