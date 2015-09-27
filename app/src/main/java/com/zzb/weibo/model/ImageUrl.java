package com.zzb.weibo.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.zzb.weibo.common.ImageUtils;

/**
 * 图片地址
 * Created by ZZB on 2015/9/15.
 */
public class ImageUrl {
    @SerializedName("thumbnail_pic")
    private String thumbUrl;
    public String getUrl(){
        return getMiddleUrl();
    }
    public String getMiddleUrl(){
        return TextUtils.isEmpty(thumbUrl) ? thumbUrl : ImageUtils.getMiddleUrl(thumbUrl);
    }
    public String getLargeUrl(){
        return TextUtils.isEmpty(thumbUrl) ? thumbUrl : ImageUtils.getLargeUrl(thumbUrl);
    }
}
