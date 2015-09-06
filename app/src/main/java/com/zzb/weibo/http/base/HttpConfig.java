package com.zzb.weibo.http.base;

import com.zzb.weibo.common.Config;

/**
 * Created by ZZB on 2015/9/1.
 */
public class HttpConfig {
    public static final String AUTH_END_POINT = "https://api.weibo.com/oauth2";
    public static final String END_POINT = "https://api.weibo.com/2";
    //请求用户授权Token
    public static final String AUTH_URL =  AUTH_END_POINT + "/authorize?client_id=" + Config.APP_KEY + "&response_type=code&redirect_uri=" + Config.REDIRECT_URL + "&scope=all&display=mobile";
    //OAuth2的access_token接口
    public static final String ACCESS_TOKEN_URL = "/access_token?client_id=" + Config.APP_KEY + "&client_secret=" + Config.APP_SECRET + "&grant_type=authorization_code&redirect_uri=" + Config.REDIRECT_URL;




}
