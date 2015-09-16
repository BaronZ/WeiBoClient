package com.zzb.weibo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zzb.weibo.common.WeiBoTimeUtils;

import java.util.List;

/**
 * 微博内容
 * Created by ZZB on 2015/8/30.
 */
public class Status {
    @Expose
    @SerializedName("created_at")
    public String createdAt;//微博创建时间
    @Expose
    public long id;//微博ID
    @Expose
    public long mid;//微博MID
    @Expose
    public String idstr;//字符串型的微博ID
    @Expose
    public String text;//微博信息内容
    @Expose
    public String source;//微博来源
    @Expose
    public boolean favorited;//是否已收藏，true：是，false：否
    @Expose
    public boolean truncated;//是否被截断，true：是，false：否
//    @Expose
//    public String inReplyToStatusId;//（暂未支持）回复ID
//    @Expose
//    public String inReplyToUserId;//（暂未支持）回复人UID
//    @Expose
//    public String inReplyToScreenName;//（暂未支持）回复人昵称
    @Expose
    @SerializedName("thumbnail_pic")
    public String thumbnailPic;//缩略图片地址，没有时不返回此字段
    @Expose
    @SerializedName("bmiddle_pic")
    public String bmiddlePic;//中等尺寸图片地址，没有时不返回此字段
    @Expose
    @SerializedName("original_pic")
    public String originalPic;//原始图片地址，没有时不返回此字段
    @SerializedName("pic_urls")
    public List<ImageUrl> picUrls;
    @Expose
    public Geo geo;//地理信息字段
    @Expose
    public User user;//微博作者的用户信息字段
    @Expose
    @SerializedName("retweeted_status")
    public Status retweetedStatus;//被转发的原微博信息字段，当该微博为转发微博时返回
    @Expose
    @SerializedName("reposts_count")
    public int repostsCount;//转发数
    @Expose
    @SerializedName("comments_count")
    public int commentsCount;//评论数
    @Expose
    @SerializedName("attitudes_count")
    public int attitudesCount;//表态数
//    @Expose
//    public int mlevel;//暂未支持
    @Expose
    public Visible visible;//微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
    @Expose
    @SerializedName("pic_ids")
    public Object picIds;//微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。
    @Expose
    public Object[] ad;//微博流内的推广微博ID

//    private String friendlyTime;
    public String getFriendlyTime(){
//        if(TextUtils.isEmpty(friendlyTime)){//加成员变量会导致时间只获取一次不刷新
        return WeiBoTimeUtils.getFriendlyTime(createdAt);
//        }
//        return friendlyTime;
    }

}
