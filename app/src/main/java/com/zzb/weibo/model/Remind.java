package com.zzb.weibo.model;

/**
 * 消息未读数
 * Created by ZZB on 2015/8/31.
 */
public class Remind {
    @Expose
    public int status;//新微博未读数
    @Expose
    public int follower;//新粉丝数
    @Expose
    public int cmt;//新评论数
    @Expose
    public int dm;//新私信数
    @Expose
    @SerializedName("mention_status")
    public int mentionStatus;//新提及我的微博数
    @Expose
    @SerializedName("mention_cmt")
    public int mentionCmt;//新提及我的评论数
    @Expose
    public int group;//微群消息未读数
    @Expose
    @SerializedName("private_group")
    public int privateGroup;//私有微群消息未读数
    @Expose
    public int notice;//新通知未读数
    @Expose
    public int invite;//新邀请未读数
    @Expose
    public int badge;//新勋章数
    @Expose
    public int photo;//相册消息未读数
    @Expose
    public int msgbox;//{{{3}}}
}
