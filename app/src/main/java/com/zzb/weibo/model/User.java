package com.zzb.weibo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 用户
 * Created by ZZB on 2015/8/30.
 */
public class User {
    @Expose
    public long id;//用户UID
    @Expose
    public String idstr;//字符串型的用户UID
    @Expose
    @SerializedName("screen_name")
    public String screenName;//用户昵称
    public String name;//友好显示名称
    @Expose
    public int province;//用户所在省级ID
    @Expose
    public int city;//用户所在城市ID
    @Expose
    public String location;//用户所在地
    @Expose
    public String description;//用户个人描述
    @Expose
    public String url;//用户博客地址
    @Expose
    @SerializedName("profile_image_url")
    public String profileImageUrl;//用户头像地址（中图），50×50像素
    @Expose
    @SerializedName("profile_url")
    public String profileUrl;//用户的微博统一URL地址
    @Expose
    public String domain;//用户的个性化域名
    @Expose
    public String weihao;//用户的微号
    @Expose
    public String gender;//性别，m：男、f：女、n：未知
    @Expose
    @SerializedName("followers_count")
    public int followersCount;//粉丝数
    @Expose
    @SerializedName("friends_count")
    public int friendsCount;//关注数
    @Expose
    @SerializedName("statuses_count")
    public int statusesCount;//微博数
    @Expose
    @SerializedName("favourites_count")
    public int favouritesCount;//收藏数
    @Expose
    @SerializedName("created_at")
    public String createdAt;//用户创建（注册）时间
    //    @Expose
//    public boolean following;//暂未支持
    @Expose
    @SerializedName("allow_all_act_msg")
    public boolean allowAllActMsg;//是否允许所有人给我发私信，true：是，false：否
    @Expose
    @SerializedName("geo_enabled")
    public boolean geoEnabled;//是否允许标识用户的地理位置，true：是，false：否
    @Expose
    public boolean verified;//是否是微博认证用户，即加V用户，true：是，false：否
    //    @Expose
//    public int verifiedType;//暂未支持
    @Expose
    public String remark;//用户备注信息，只有在查询用户关系时才返回此字段
    @Expose
    public Status status;//用户的最近一条微博信息字段
    @Expose
    @SerializedName("allow_all_comment")
    public boolean allowAllComment;//是否允许所有人对我的微博进行评论，true：是，false：否
    @Expose
    @SerializedName("avatar_large")
    public String avatarLarge;//用户头像地址（大图），180×180像素
    @Expose
    @SerializedName("avatar_hd")
    public String avatarHd;//用户头像地址（高清），高清头像原图
    @Expose
    @SerializedName("verified_reason")
    public String verifiedReason;//认证原因
    @Expose
    @SerializedName("follow_me")
    public boolean followMe;//该用户是否关注当前登录用户，true：是，false：否
    @Expose
    @SerializedName("online_status")
    public int onlineStatus;//用户的在线状态，0：不在线、1：在线
    @Expose
    @SerializedName("bi_followers_count")
    public int biFollowersCount;//用户的互粉数
    @Expose
    public String lang;//用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
}
