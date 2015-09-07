package com.zzb.weibo.http.base;

/**
 * Http相关设置
 * Created by ZZB on 2015/9/1.
 */
public class HttpConfig {
    public static final String AUTH_END_POINT = "https://api.weibo.com/oauth2";
    public static final String END_POINT = "https://api.weibo.com/2";
    //连接超时时间10秒
    public static final long CONNECTION_TIMEOUT_IN_SECOND = 10;
    public static final long READ_TIMEOUT_IN_SECOND = 10;
    public static final long WRITE_TIMEOUT_IN_SECOND = 10;
}
