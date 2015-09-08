package com.zzb.weibo.mvp.presenter;

import com.zzb.mvp.MvpBasePresenter;
import com.zzb.weibo.mvp.view.MyHomePageView;

/**
 * 我的首页相关
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePagePresenter extends MvpBasePresenter<MyHomePageView>{

    public void loadStatus(){
        getView().showLoading();
        getView().hideLoading();
    }
}
