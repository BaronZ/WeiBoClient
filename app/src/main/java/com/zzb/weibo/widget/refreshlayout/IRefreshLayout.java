package com.zzb.weibo.widget.refreshlayout;

import android.view.View;

/**
 * Created by ZZB on 2015/9/13.
 */
public interface IRefreshLayout {

    /**
     * 手动触发刷新
     */
    public void manualRefresh();

    public void setCanLoadMore(boolean canLoadMore);

    public void completeRefresh();

    public void completeLoadMore();

    public void setEmptyView(View emptyView);
    public void setEmptyText(String text);
    public void setEmptyViewListener(View.OnClickListener listener);
    public void showEmptyView();

    public void setErrorView(View errorView);
    public void setErrorText(String text);
    public void setErrorViewListener(View.OnClickListener listener);
    public void showErrorView();


    public void setCallBack(final RefreshCallBack callBack);


    public static interface RefreshCallBack {
        public void onRefresh();

        public void onLoadMore();
    }
}
