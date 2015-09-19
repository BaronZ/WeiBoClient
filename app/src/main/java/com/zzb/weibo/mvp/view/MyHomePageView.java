package com.zzb.weibo.mvp.view;

import com.zzb.mvp.MvpView;
import com.zzb.weibo.model.Status;

import java.util.List;

/**
 * 我的首页相关
 * Created by ZZB on 2015/9/8.
 */
public interface MyHomePageView extends MvpView {

    void showLoading();
    void hideLoading();
    void onRefreshStatusesSuccess(List<Status> statuses);
    void onLoadMoreStatusesSuccess(List<Status> statuses);
    void onLoadStatusesFailed();
    void onNoMoreStatusToLoad();
}
