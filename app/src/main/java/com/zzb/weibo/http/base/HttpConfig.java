package com.zzb.weibo.http.base;

import java.util.concurrent.TimeUnit;

/**
 * Http相关设置
 * Created by ZZB on 2015/9/1.
 */
public class HttpConfig {
    public static final String AUTH_END_POINT = "https://api.weibo.com/oauth2";
    public static final String END_POINT = "https://api.weibo.com/2";
    //超时时间10秒
    public static final long TIME_OUT = TimeUnit.SECONDS.toMillis(10);
}
