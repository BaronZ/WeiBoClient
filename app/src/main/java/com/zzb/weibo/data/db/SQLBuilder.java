package com.zzb.weibo.data.db;

import com.zzb.weibo.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZB on 2015/9/9.
 */
public class SQLBuilder {
    private static final String TYPE_INT = "INTEGER";
    private static final String TYPE_TEXT = "TEXT";

    public static class Alter{
        private List<String> mIntColNames = new ArrayList<>();
        private List<String> mTextColNames = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        public Alter addIntCol(String colName){
            mIntColNames.add(colName);
            return this;
        }
        public Alter addTextCol(String colName){
            mTextColNames.add(colName);
            return this;
        }
        public String build(String tableName){
            //db.execSQL("alter table upload_tasklist add is_add INTEGER null;");
            for(String colName : mIntColNames){
                sql.append("alter ").append(tableName).append(" add ").append(colName).append(" ").append(TYPE_INT).append(" null;");
            }
            for(String colName : mTextColNames){
                sql.append("alter ").append(tableName).append(" add ").append(colName).append(" ").append(TYPE_TEXT).append(" null;");
            }
            return sql.toString();
        }
    }
    /**
     * 创建数据库表
     *@author ZZB
     *created at 2015/9/9 18:10
     */
    public static class Create{
        private List<String> mIntCols = new ArrayList<>();
        private List<String> mTextCols = new ArrayList<>();

        public static Create getInstance(){
            return new Create();
        }
        public Create addIntColumn(String colName){
            mIntCols.add(colName);
            return this;
        }
        public Create addTextColumn(String colName){
            mTextCols.add(colName);
            return this;
        }
        public String build(String tableName){
            StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE ").append(tableName).append(" (");
            for(String intCol : mIntCols){
                sql.append(intCol).append(" ").append(TYPE_INT).append(",");
            }
            for(String textCol : mTextCols){
                sql.append(textCol).append(" ").append(TYPE_TEXT).append(",");
            }
            StringUtils.deleteLastChars(sql, ",");
            sql.append(");");
            return sql.toString();
        }
    }


}
