package com.zzb.weibo.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.zzb.weibo.model.AccessToken;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZZB on 2015/9/1.
 */
public class AccessTokenKeeper {
    private static final String TAG = AccessTokenKeeper.class.getSimpleName();
    private static final String PREFERENCES_NAME = "AUTH_ACCESS_TOKEN";
    private static final String KEY_ACCESS_TOKEN  = "KEY_ACCESS_TOKEN";
    private static final String KEY_EXPIRES_TIME    = "KEY_EXPIRES_TIME";
    private static String ACCESS_TOKEN = "";
    /**
     * 获取AccessToken
     *@author ZZB
     *created at 2015/9/6 14:30
     */
    public static String getAccessToken(Context context){
        if(TextUtils.isEmpty(ACCESS_TOKEN)){
            ACCESS_TOKEN = readAccessToken(context).token;
        }
        return ACCESS_TOKEN;
    }
    /**
     * 保存 Token 对象到 SharedPreferences。
     *
     * @param context 应用程序上下文环境
     * @param token   Token 对象
     */
    public static void writeAccessToken(Context context, AccessToken token) {
        Log.i(TAG, "保存access token:" + token.token + " expires in:" + token.expiresIn);
        if (null == context || null == token) {
            return;
        }
        token.expiresTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(token.expiresIn);
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_ACCESS_TOKEN, token.token);
        editor.putLong(KEY_EXPIRES_TIME, token.expiresTime);
        editor.commit();
    }

    /**
     * 从 SharedPreferences 读取 Token 信息。
     *
     * @param context 应用程序上下文环境
     *
     * @return 返回 Token 对象
     */
    public static AccessToken readAccessToken(Context context) {
        if (null == context) {
            return null;
        }

        AccessToken token = new AccessToken();
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        token.token = pref.getString(KEY_ACCESS_TOKEN, "");
        token.expiresTime = pref.getLong(KEY_EXPIRES_TIME, 0);

        return token;
    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     *
     * @param context 应用程序上下文环境
     */
    public static void clear(Context context) {
        if (null == context) {
            return;
        }
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
