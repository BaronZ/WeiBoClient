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
     * 第一次加载，如果有缓存，加载缓存，没有缓存才加载网络
     *created at 2015/9/20 2:19
     */
    public void loadCacheOrNetStatus(){
        long lastId = 0;
        Observable<List<Status>> ob = Observable
                //数据库拿的话，是比该id小的微博，所以首页要无限大
                .concat(getLoadFromDbRx(Long.MAX_VALUE), getLoadFromNetRx(lastId))//如果有缓存，直接用，没缓存，拿网络
                .takeFirst(statuses -> !ListUtils.isEmpty(statuses));
        onObservablePrepared(ob, true);
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
    public void loadMoreStatus(long lastId) {
        loadStatuses(lastId);
    }
    private void loadStatuses(final long lastId){
        final boolean isRefresh = (lastId == 0);
        boolean isNetAvailable = NetUtils.isNetworkAvailable(mContext);
        getView().showLoading();
        Observable<List<Status>> observable = null;
        if (isNetAvailable) {//有网络，直接从网络获取，并缓存到数据库
            observable = getLoadFromNetRx(lastId);
        } else {//无网络，从数据库拿
            observable = getLoadFromDbRx(lastId);
        }
        onObservablePrepared(observable, isRefresh);
    }
    private void onObservablePrepared(Observable<List<Status>> ob, boolean isRefresh){
        ob.observeOn(AndroidSchedulers.mainThread())
                .subscribe(statuses -> {
                            onDataLoaded(statuses, isRefresh);
                        }, error -> {
                            if (isViewAttached()) {
                                getView().hideLoading();
                                getView().onLoadStatusesFailed();
                            }
                        }, () -> {
                            if (isViewAttached()) {
                                getView().hideLoading();
                            }
                        }
                );
    }
    private Observable<List<Status>> getLoadFromNetRx(long lastId){
        return RetrofitHelper.getApi(WeiBoApi.class)
                .getFriendsTimeLine(lastId, Config.LOAD_WEIBO_SIZE).map(rootData -> rootData.statuses)
                .doOnNext(this::cacheStatuses);//statuses -> cacheStatuses(statuses)
    }
    private Observable<List<Status>> getLoadFromDbRx(long lastId){
        return mDao.get(lastId, Config.LOAD_WEIBO_SIZE);
    }
    /**
     * 缓存网络加载的数据
     *created at 2015/9/13 0:30
     */
    private void cacheStatuses(List<Status> statuses) {
        if (!ListUtils.isEmpty(statuses)) {
            long startId = statuses.get(statuses.size() - 1).id;
            long endId = statuses.get(0).id;
            mDao.delete(startId, endId).subscribe();
            mDao.save(statuses).subscribe();
        }
    }
    private void onDataLoaded(List<Status> statuses, boolean isRefresh){
        if(!isViewAttached()){
            return;
        }
        if(isRefresh){//刷新
            getView().onRefreshStatusesSuccess(statuses);
        }else{//加载更多
            if(ListUtils.isEmpty(statuses)){
                getView().onNoMoreStatusToLoad();
            }else{
                statuses.remove(0);//因为加载更多会包含上一个id的微博，所以这里要删除一个解决重复问题
                if(ListUtils.isEmpty(statuses)){
                    getView().onNoMoreStatusToLoad();
                }else{
                    getView().onLoadMoreStatusesSuccess(statuses);
                }
            }
        }
    }
}
