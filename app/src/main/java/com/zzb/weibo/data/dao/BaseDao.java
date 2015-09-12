package com.zzb.weibo.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.Nullable;

import com.zzb.weibo.data.db.SQLiteHelper;

import java.util.List;

/**
 * dao基类
 * Created by ZZB on 2015/9/12.
 */
public class BaseDao {

    protected Context mContext;
    protected SQLiteHelper mDbHelper;

    public BaseDao(Context context) {
        mContext = context.getApplicationContext();
        mDbHelper = new SQLiteHelper(mContext);
    }

    protected Cursor query(String tableName, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(tableName);
        Cursor cursor = builder.query(mDbHelper.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);
        return cursor;
    }


    protected Cursor query(String tableName, String[] projectionIn,
                           String selection, String[] selectionArgs, String groupBy,
                           String having, String sortOrder, String limit) {

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(tableName);
        Cursor cursor = builder.query(mDbHelper.getReadableDatabase(),
                projectionIn, selection, selectionArgs, groupBy, having, sortOrder, limit
        );

        return cursor;

    }


    protected Cursor rawQuery(String sql, String[] args) {
        Cursor cursor = mDbHelper.getReadableDatabase().rawQuery(sql, args);
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }


    protected long insert(String tableName, ContentValues cv) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db.insert(tableName, null, cv);
    }

    protected void batchInsert(String tableName, List<ContentValues> cvs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (ContentValues cv : cvs) {
                db.replace(tableName, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    protected void delete(String tableName) {
        this.delete(tableName, null, null);
    }

    public void delete(String tableName, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.delete(tableName, whereClause, whereArgs);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db.update(table, values, whereClause, whereArgs);
    }


    public static String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    public static int getInt(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndex(column));
    }

    public static double getDouble(Cursor cursor, String column) {
        return cursor.getDouble(cursor.getColumnIndex(column));
    }

    public static boolean getBoolean(Cursor cursor, String column) {
        return getInt(cursor, column) == 0 ? false : true;
    }

}
