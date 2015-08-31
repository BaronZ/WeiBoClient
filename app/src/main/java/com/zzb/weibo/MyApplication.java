package com.zzb.weibo;

import android.app.Application;
import android.content.Context;

import com.zzb.weibo.common.AppInit;

/**
 * Created by ZZB on 2015/8/31.
 */
public class MyApplication extends Application {

    public static Context APP_CONTEXT;
    @Override
    public void onCreate() {
        super.onCreate();
        APP_CONTEXT = this;
        AppInit.getInstance().run();

    }
}
