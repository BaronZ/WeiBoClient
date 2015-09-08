package com.zzb.weibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzb.weibo.R;

/**
 * 我的首页
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    public MyHomePageFragment getInstance(){
        return new MyHomePageFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.frgm_my_home_page, container, false);
        mRecyclerView = $(R.id.recycler_view);
        return mContentView;
    }

    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }
}
