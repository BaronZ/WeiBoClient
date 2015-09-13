package com.zzb.weibo.mvp.presenter;

import android.content.Context;

import com.zzb.library.utils.ListUtils;
import com.zzb.library.utils.NetUtils;
import com.zzb.mvp.MvpBasePresenter;
import com.zzb.weibo.common.Config;
import com.zzb.weibo.data.dao.MyHomePageDao;
import com.zzb.weibo.http.api.WeiBoApi;
import com.zzb.weibo.http.base.RetrofitHelper;
import com.zzb.weibo.model.Status;
import com.zzb.weibo.mvp.view.MyHomePageView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 我的首页相关
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePagePresenter extends MvpBasePresenter<MyHomePageView> {
    private MyHomePageDao mDao;

    public MyHomePagePresenter(Context context) {
        super(context);
        mDao = new MyHomePageDao(context);
    }

    /**
     * 刷新微博
     *created at 2015/9/13 0:20
     */
    public void refreshStatus(){
        boolean isNetAvailable = NetUtils.isNetworkAvailable(mContext);
        if(isNetAvailable){
            loadStatuses(0);
        }else{//无网络，无法刷新
            if(isViewAttached()){
                getView().hideLoading();
            }
        }

    }
    /**
     * 加载更多微博
     * @param lastId 该id之前的微博
     */
    public void loadMoreStatus(final long lastId) {
        loadStatuses(lastId);
    }
    private void loadStatuses(final long lastId){
        final boolean isRefresh = (lastId == 0);
        boolean isNetAvailable = NetUtils.isNetworkAvailable(mContext);
        getView().showLoading();
        Observable<List<Status>> observable = null;
        if (isNetAvailable) {//有网络，直接从网络获取，并缓存到数据库
            observable = RetrofitHelper.getApi(WeiBoApi.class)
                    .getFriendsTimeLine(lastId, Config.LOAD_WEIBO_SIZE).map(rootData -> rootData.statuses)
                    .doOnNext(statuses -> cacheStatuses(statuses));

        } else {//无网络，从数据库拿
            observable = mDao.get(lastId, Config.LOAD_WEIBO_SIZE);
        }
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(statuses -> {
                            onDataLoaded(statuses, isRefresh);
                        }, error -> {
                            if(isViewAttached()){
                                getView().onLoadStatusesFailed();
                            }
                        }, () -> {
                            if(isViewAttached()){
                                getView().hideLoading();
                            }
                        }
                );
    }
    /**
     * 缓存网络加载的数据
     *created at 2015/9/13 0:30
     */
    private void cacheStatuses(List<Status> statuses) {
        if (!ListUtils.isEmpty(statuses)) {
            long startId = statuses.get(statuses.size() - 1).id;
            long endId = statuses.get(0).id;
            mDao.delete(startId, endId);
            mDao.save(statuses);
        }
    }
    private void onDataLoaded(List<Status> statuses, boolean isRefresh){
        if(isRefresh){//刷新
            if(isViewAttached()){
                getView().onRefreshStatusesSuccess(statuses);
            }
        }else{//加载更多
            getView().onLoadMoreStatusesSuccess(statuses);
        }
    }
}
