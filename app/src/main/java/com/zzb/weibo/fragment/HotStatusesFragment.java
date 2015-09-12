package com.zzb.weibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzb.weibo.R;

/**
 * 热门微博
 * Created by ZZB on 2015/9/12.
 */
public class HotStatusesFragment extends BaseFragment{
    public static HotStatusesFragment getInstance(){
        return new HotStatusesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.frgm_hot_statuses, container, false);
        return mContentView;
    }

    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }
}
