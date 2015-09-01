package com.zzb.weibo.common;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.zzb.weibo.BuildConfig;
import com.zzb.weibo.MyApplication;

/**
 * Created by ZZB on 2015/8/31.
 */
public class AppInit {
    private static AppInit instance;

    public static AppInit getInstance(){
        if(instance == null){
            instance = new AppInit();
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
        MobclickAgent.openActivityDurationTrack(false);
    }

    /**
     * 调试环境配置
     *created at 2015/8/31 17:35
     */
    private void debug(){

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
