package com.zzb.weibo.common;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 微博时间工具类
 * Created by ZZB on 2015/9/16.
 */
public class WeiBoTimeUtils {
    //微博时间格式
    public static final String  TIME_PATTERN = "EEE MMM d HH:mm:ss Z yyyy";
    private static SoftReference<SimpleDateFormat> sRefDateFormat;
    private static SimpleDateFormat getDateFormat(){
        if(sRefDateFormat == null){
            SimpleDateFormat df = new SimpleDateFormat(TIME_PATTERN, Locale.ENGLISH);
            sRefDateFormat = new SoftReference<>(df);
        }
        SimpleDateFormat df = sRefDateFormat.get();
        if(df == null){
            df = new SimpleDateFormat(TIME_PATTERN, Locale.ENGLISH);
            sRefDateFormat = new SoftReference<SimpleDateFormat>(df);
        }
        return df;
    }

    /**
     * 把微博返回的时候转为可读时间
     * @param weiboTime 微博返回时间，格式为 EEE MMM d HH:mm:ss Z yyyy
     * @return
     */
    public static String getFriendlyTime(String weiboTime){
        try {
            Date date = getDateFormat().parse(weiboTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TODO
        return "";
    }
}
