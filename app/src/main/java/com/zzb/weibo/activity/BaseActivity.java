package com.zzb.weibo.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by ZZB on 2015/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected <T extends View> T $(int id){
        return (T) findViewById(id);
    }
    //获取类名，用来给友盟统计使用
    protected abstract String getClassName();
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClassName());
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClassName());
        MobclickAgent.onPause(this);
    }
}
