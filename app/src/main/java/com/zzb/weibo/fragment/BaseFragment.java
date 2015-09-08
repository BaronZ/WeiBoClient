package com.zzb.weibo.fragment;

import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.zzb.mvp.BaseMVPFragment;

/**
 * Fragment基类
 * Created by ZZB on 2015/9/1.
 */
public abstract class BaseFragment extends BaseMVPFragment {
    protected View mContentView;
    protected abstract String getClassName();

    protected <T extends View> T $(int id){
        return (T) mContentView.findViewById(id);
    }
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
