package com.zzb.weibo.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.zzb.weibo.MyApplication;
import com.zzb.weibo.R;

/**
 * Snackbar工具类
 * Created by ZZB on 2015/9/20.
 */
public class SnackbarUtils {

    public static void showLong(View anchorView, String text){
        Snackbar.make(anchorView, text, Snackbar.LENGTH_LONG).show();
    }
    public static void showNoNetWork(View anchorView){
        showLong(anchorView, MyApplication.APP_CONTEXT.getString(R.string.no_net_work));
    }
}
