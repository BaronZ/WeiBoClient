package com.zzb.weibo.data.db;

/**
 * 数据库表字段
 * Created by ZZB on 2015/9/12.
 */
public class DBColumns {

    /**
     * 我的首页数据库表字段
     *created at 2015/9/12 20:50
     */
    public static interface MyHomePage{
        String TABLE_NAME = "MyHomePage";
        String JSON = "json";
        String ID = "id";
        String CREATE_TABLE_SQL = SQLBuilder.Create.getInstance()
                .addIntColumn(ID).addTextColumn(JSON).build(TABLE_NAME);
    }

}
