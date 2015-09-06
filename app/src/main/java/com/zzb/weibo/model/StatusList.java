package com.zzb.weibo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 微博列表返回对象
 * Created by ZZB on 2015/9/6.
 */
public class StatusList {
    public List<Status> statuses;
//    public Object[] advertises;
//    public Object[] ad;
    @SerializedName("hasvisible")
    public boolean hasVisible;
//    @SerializedName("previous_cursor")// 暂时不支持
//    public long previousCursor;
//    @SerializedName("next_cursor")// 暂时不支持
//    public long nextCursor;
    @SerializedName("total_number")
    public int totalNumber;
    public int interval;
    @SerializedName("uve_blank")
    public int uveBlank;
    @SerializedName("since_id")
    public long sinceId;
    @SerializedName("max_id")
    public long maxId;
    @SerializedName("has_unread")
    public int hasUnread;
}
