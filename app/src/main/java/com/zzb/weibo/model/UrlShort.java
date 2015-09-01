package com.zzb.weibo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 短链
 * Created by ZZB on 2015/8/31.
 */
public class UrlShort {
    @Expose
    @SerializedName("url_short")
    public String urlShort;//短链接
    @Expose
    @SerializedName("url_long")
    public String urlLong;//原始长链接
    @Expose
    public int type;//链接的类型，0：普通网页、1：视频、2：音乐、3：活动、5、投票
    @Expose
    public boolean result;//短链的可用状态，true：可用、false：不可用。
}
