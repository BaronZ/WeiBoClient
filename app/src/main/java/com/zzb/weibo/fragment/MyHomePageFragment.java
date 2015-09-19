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
import com.zzb.weibo.utils.SnackbarUtils;
import com.zzb.weibo.widget.refreshlayout.CustomRefreshLayout;
import com.zzb.weibo.widget.refreshlayout.IRefreshLayout;

import java.util.List;

/**
 * 我的首页
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageFragment extends BaseFragment implements MyHomePageView, IRefreshLayout.RefreshCallBack{
    private MyHomePagePresenter mPresenter;
    private MyHomePageAdapter mAdapter;
    private CustomRefreshLayout mRefreshLayout;
    private boolean mIsFirstLoad = true;

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
        mRefreshLayout.manualRefresh();
        return mContentView;
    }

    private void initViews() {
        RecyclerView recyclerView = $(R.id.recycler_view);
        mAdapter = new MyHomePageAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mRefreshLayout = $(R.id.swipe_refresh_layout);
        mRefreshLayout.setCallBack(this);
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
        mRefreshLayout.completeRefresh();
        mRefreshLayout.completeLoadMore();
    }

    @Override
    public void onRefreshStatusesSuccess(List<Status> statuses) {
        mIsFirstLoad = false;
        mAdapter.setData(statuses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreStatusesSuccess(List<Status> statuses) {
        mAdapter.addData(statuses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadStatusesFailed() {
        mIsFirstLoad = false;
        SnackbarUtils.showLong(mRefreshLayout, "加载微博出问题了");
    }

    @Override
    public void onNoMoreStatusToLoad() {
        setCanLoadMore(false);
        SnackbarUtils.showLong(mRefreshLayout, "没数据咯");
    }

    @Override
    public void onRefresh() {
        setCanLoadMore(true);
        if(mIsFirstLoad){
            mPresenter.loadCacheOrNetStatus();
        }else{
            mPresenter.refreshStatus();
        }
    }

    @Override
    public void onLoadMore() {
        long lastId = mAdapter.getLastStatusId();
        mPresenter.loadMoreStatus(lastId);
    }

    private void setCanLoadMore(boolean canLoadMore){
        mRefreshLayout.setCanLoadMore(canLoadMore);
        mAdapter.setCanLoadMore(canLoadMore);
    }
}
