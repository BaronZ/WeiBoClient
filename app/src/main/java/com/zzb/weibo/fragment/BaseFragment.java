package com.zzb.weibo.fragment;

import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by ZZB on 2015/9/1.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract String getClassName();
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClassName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClassName());
    }
}
