package com.zzb.weibo.common;

import com.google.gson.Gson;

/**
 * Json工具类
 * Created by ZZB on 2015/9/12.
 */
public class GsonHelper {

    public static <T> T jsonToObj(Class<T> cls, String json){
        Gson gson = new Gson();
        T obj = gson.fromJson(json, cls);
        return obj;
    }
    public static String objToJson(Object obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}
