package com.zzb.weibo.common;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.zzb.weibo.BuildConfig;
import com.zzb.weibo.MyApplication;

/**
 * Created by ZZB on 2015/8/31.
 */
public class AppInit {
    private static AppInit instance;
    private static Context sAppContext;
    public static AppInit getInstance(Context context){
        if(instance == null){
            instance = new AppInit();
            sAppContext = context;
        }
        return instance;
    }
    private AppInit(){
    }

    public void run() {
        common();
        if(BuildConfig.DEBUG){
            debug();
        }else{
            product();
        }
    }
    private void common(){
        initBugly();
        initUmeng();
    }

    private void initUmeng() {
        AnalyticsConfig.setAppkey(sAppContext, BuildConfig.UMENG_APP_KEY);
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);
        MobclickAgent.openActivityDurationTrack(false);
    }

    /**
     * 调试环境配置
     *created at 2015/8/31 17:35
     */
    private void debug(){
        Stetho.initializeWithDefaults(sAppContext);
    }
    /**
     * 正式环境配置
     *created at 2015/8/31 17:34
     */
    private void product(){

    }

    /**
     * 异常监控bugly初始化
     *created at 2015/8/31 17:37
     */
    private void initBugly(){
        CrashReport.initCrashReport(MyApplication.APP_CONTEXT, BuildConfig.BUGLY_APP_ID, BuildConfig.DEBUG);
    }
}
