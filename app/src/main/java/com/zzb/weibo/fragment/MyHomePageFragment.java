package com.zzb.weibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 我的首页
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageFragment extends BaseFragment {
    private View mContentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }
}
