package com.zzb.weibo.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.zzb.weibo.common.GsonHelper;
import com.zzb.weibo.data.db.DBColumns;
import com.zzb.weibo.model.Status;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 我的首页相关数据库操作
 * Created by ZZB on 2015/9/12.
 */
public class MyHomePageDao extends BaseDao {
    private static final String TABLE_NAME = DBColumns.MyHomePage.TABLE_NAME;

    public MyHomePageDao(Context context) {
        super(context);
    }

    /**
     * @param lastId 起始id，获取比该id小的微博, 长度为limit
     * @param limit  数据的长度
     * @return Observable<List<Status>>
     */
    public Observable<List<Status>> get(final long lastId, final int limit) {
        return Observable.create(new Observable.OnSubscribe<List<Status>>() {
            @Override
            public void call(Subscriber<? super List<Status>> subscriber) {
                subscriber.onNext(syncGet(lastId, limit));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());

    }

    /**
     * 保存微博
     * created at 2015/9/12 23:45
     */
    public Observable save(final List<Status> statuses) {
        return Observable.create(subscriber -> {
            syncSave(statuses);
            subscriber.onNext(null);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    /**
     * @param idFrom id起始
     * @param idTo   id结束
     * @return Observable
     */
    public Observable delete(final long idFrom, final long idTo) {
        return Observable.create(subscriber -> {
            subscriber.onNext(syncDelete(idFrom, idTo));
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 同步获取微博
     * created at 2015/9/13 14:14
     */
    private List<Status> syncGet(long lastId, int limit) {
        List<Status> statuses = new ArrayList<>();
        Cursor cursor = query(TABLE_NAME, null, DBColumns.MyHomePage.ID + "< ?",
                new String[]{String.valueOf(lastId)}, null, null, null, String.valueOf(limit));
        if(cursor != null){
            try {
                while(cursor.moveToNext()){
                    String json = getString(cursor, DBColumns.MyHomePage.JSON);
                    Status status = GsonHelper.jsonToObj(Status.class, json);
                    statuses.add(status);
                }
            }finally {
                cursor.close();
            }
        }
        return statuses;
    }

    /**
     * @param idFrom id起始
     * @param idTo   id结束
     * @return 删除的条数
     */
    private int syncDelete(long idFrom, long idTo) {
        if(idFrom > idTo){
            throw new IllegalArgumentException("id from > id to");
        }
        int deleted = delete(TABLE_NAME, DBColumns.MyHomePage.ID + " between ? and ?", new String[]{String.valueOf(idFrom), String.valueOf(idTo)});
        return deleted;
    }

    /**
     * 同步保存
     * created at 2015/9/12 23:47
     */
    private void syncSave(List<Status> statuses) {
        List<ContentValues> cvs = new ArrayList<>();
        for (Status status : statuses) {
            ContentValues cv = getContentValues(status);
            cvs.add(cv);
        }
        batchInsert(TABLE_NAME, cvs);
    }

    private ContentValues getContentValues(Status status) {
        ContentValues cv = new ContentValues();
        cv.put(DBColumns.MyHomePage.ID, status.id);
        String json = GsonHelper.objToJson(status);
        cv.put(DBColumns.MyHomePage.JSON, json);
        return cv;
    }
}
