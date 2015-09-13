package com.zzb.weibo.widget.refreshlayout;

/**
 * Created by ZZB on 2015/9/13.
 */
public interface IRefreshLayout {

    void startRefresh();
    void startLoadMore();
    void endRefresh();
    void endLoadMore();
}
