package com.zzb.weibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzb.weibo.R;
import com.zzb.weibo.adapter.MyHomePageAdapter;
import com.zzb.weibo.model.Status;
import com.zzb.weibo.mvp.presenter.MyHomePagePresenter;
import com.zzb.weibo.mvp.view.MyHomePageView;

import java.util.List;

/**
 * 我的首页
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageFragment extends BaseFragment implements MyHomePageView{
    private RecyclerView mRecyclerView;
    private MyHomePagePresenter mPresenter;
    private MyHomePageAdapter mAdapter;

    public static MyHomePageFragment getInstance(){
        return new MyHomePageFragment();
    }

    @Override
    protected void initPresenters() {
        super.initPresenters();
        mPresenter = new MyHomePagePresenter(getActivity());
        mPresenters.add(mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.frgm_my_home_page, container, false);
        initViews();
        mPresenter.refreshStatus();
        return mContentView;
    }

    private void initViews() {
        mRecyclerView = $(R.id.recycler_view);
        mAdapter = new MyHomePageAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onRefreshStatusesSuccess(List<Status> statuses) {
        mAdapter.setData(statuses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreStatusesSuccess(List<Status> statuses) {

    }

    @Override
    public void onLoadStatusesFailed() {

    }
}
