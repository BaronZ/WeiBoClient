package com.zzb.weibo.model;

/**
 * 评论
 * Created by ZZB on 2015/8/31.
 */
public class Comment {
    @Expose
    @SerializedName("created_at")
    public String createdAt;//评论创建时间
    @Expose
    public int id;//评论的ID
    @Expose
    public String text;//评论的内容
    @Expose
    public String source;//评论的来源
    @Expose
    public User user;//评论作者的用户信息字段
    @Expose
    public String mid;//评论的MID
    @Expose
    public String idstr;//字符串型的评论ID
    @Expose
    public Status status;//评论的微博信息字段
    @Expose
    @SerializedName("reply_comment")
    public Comment replyComment;//评论来源评论，当本评论属于对另一评论的回复时返回此字段
}
