package com.zzb.weibo.http.base;

import com.zzb.weibo.common.Config;

/**
 * Created by ZZB on 2015/9/6.
 */
public class ApiUrl {
    public static class Auth{
        //请求用户授权Token
        public static final String AUTH_URL =  HttpConfig.AUTH_END_POINT + "/authorize?client_id=" + Config.APP_KEY + "&response_type=code&redirect_uri=" + Config.REDIRECT_URL + "&scope=all&display=mobile";
        //OAuth2的access_token接口
        public static final String ACCESS_TOKEN_URL = "/access_token?client_id=" + Config.APP_KEY + "&client_secret=" + Config.APP_SECRET + "&grant_type=authorization_code&redirect_uri=" + Config.REDIRECT_URL;
    }
    public static class WeiBo{
        //获取当前登录用户及其所关注用户的最新微博
        public static final String FRIENDS_TIMELINE = "/statuses/friends_timeline.json";
        //发布带图片的微博
        public static final String POST_WEIBO_WITH_PIC = "/statuses/upload.json";
        //发布纯文字的微博
        public static final String POST_WEIBO = "/statuses/update.json ";
    }
}
