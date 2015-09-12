package com.zzb.weibo.data.dao;

import android.content.ContentValues;
import android.content.Context;

import com.zzb.weibo.common.GsonHelper;
import com.zzb.weibo.data.db.DBColumns;
import com.zzb.weibo.model.Status;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 我的首页相关数据库操作
 * Created by ZZB on 2015/9/12.
 */
public class MyHomePageDao extends BaseDao {
    public MyHomePageDao(Context context) {
        super(context);
    }
    /**
     * 保存微博
     *created at 2015/9/12 23:45
     */
    public Observable save(final List<Status> statuses){
        return Observable.create(subscriber -> {
            syncSave(statuses);
            subscriber.onNext(null);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }
    /**
     * @param idFrom id起始
     * @param idTo id结束
     * @return Observable
     */
    public Observable delete(final long idFrom, final long idTo){
        return Observable.create(subscriber -> {
            subscriber.onNext(syncDelete(idFrom, idTo));
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    /**
     * @param idFrom id起始
     * @param idTo id结束
     * @return 删除的条数
     */
    private int syncDelete(long idFrom, long idTo){
        //TODO
        return 0;
    }

    /**
     * 同步保存
     *created at 2015/9/12 23:47
     */
    private void syncSave(List<Status> statuses){
        List<ContentValues> cvs = new ArrayList<>();
        for(Status status : statuses){
            ContentValues cv = getContentValues(status);
            cvs.add(cv);
            batchInsert(DBColumns.MyHomePage.TABLE_NAME, cvs);
        }
    }

    private ContentValues getContentValues(Status status){
        ContentValues cv = new ContentValues();
        cv.put(DBColumns.MyHomePage.ID, status.id);
        String json = GsonHelper.objToJson(status);
        cv.put(DBColumns.MyHomePage.JSON, json);
        return cv;
    }
}
