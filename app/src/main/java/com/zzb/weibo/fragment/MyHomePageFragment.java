package com.zzb.weibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zzb.weibo.R;
import com.zzb.weibo.adapter.MyHomePageAdapter;
import com.zzb.weibo.model.Status;
import com.zzb.weibo.mvp.presenter.MyHomePagePresenter;
import com.zzb.weibo.mvp.view.MyHomePageView;
import com.zzb.weibo.widget.refreshlayout.CustomRefreshLayout;
import com.zzb.weibo.widget.refreshlayout.IRefreshLayout;

import java.util.List;

/**
 * 我的首页
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageFragment extends BaseFragment implements MyHomePageView, IRefreshLayout.RefreshCallBack{
    private RecyclerView mRecyclerView;
    private MyHomePagePresenter mPresenter;
    private MyHomePageAdapter mAdapter;
    private CustomRefreshLayout mRefreshLayout;

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
//        mPresenter.refreshStatus();
        mRefreshLayout.manualRefresh();
        return mContentView;
    }

    private void initViews() {
        mRecyclerView = $(R.id.recycler_view);
        mAdapter = new MyHomePageAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

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

    }

    @Override
    public void onNoMoreStatusToLoad() {
        setCanLoadMore(false);
        Toast.makeText(getActivity(), "没数据咯",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        setCanLoadMore(true);
        mPresenter.refreshStatus();
    }

    @Override
    public void onLoadMore() {
        // show progressbar
        long lastId = mAdapter.getLastStatusId();
        mPresenter.loadMoreStatus(lastId);
        Toast.makeText(getActivity(), "bottom",Toast.LENGTH_SHORT).show();
    }

    private void setCanLoadMore(boolean canLoadMore){
        mRefreshLayout.setCanLoadMore(canLoadMore);
        mAdapter.setCanLoadMore(canLoadMore);
    }
}
