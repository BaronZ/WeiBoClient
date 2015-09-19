package com.zzb.weibo.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * Created by ZZB on 2015/9/12.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "RealWB";
    private static final int VERSION = 1;

    public SQLiteHelper(Context context){
        super(context.getApplicationContext(), DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBColumns.MyHomePage.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
