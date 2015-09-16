package com.zzb.weibo.common;

import android.util.Log;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 微博时间工具类
 * Created by ZZB on 2015/9/16.
 */
public class WeiBoTimeUtils {
    private static final String TAG = WeiBoTimeUtils.class.getSimpleName();
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
            Log.d(TAG, "date format miss");
        }else{
            Log.d(TAG, "date format hit");
        }
        return df;
    }

    /**
     * 把微博返回的时候转为可读时间
     * @param sourceTime 微博返回时间，格式为 EEE MMM d HH:mm:ss Z yyyy
     * @return
     */
    public static String getFriendlyTime(String sourceTime){
        String friendlyTime = sourceTime;
        long currentMillis = System.currentTimeMillis();
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(currentMillis);
        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);
        try {
            Date date = getDateFormat().parse(sourceTime);
            long sourceMillis = date.getTime();
            Calendar sourceCalendar = Calendar.getInstance();
            sourceCalendar.setTimeInMillis(sourceMillis);
            int sourceDay = sourceCalendar.get(Calendar.DAY_OF_MONTH);
            int sourceHour = sourceCalendar.get(Calendar.HOUR_OF_DAY);
            int sourceSecond = sourceCalendar.get(Calendar.SECOND);
            int sourceYear = sourceCalendar.get(Calendar.YEAR);
            int sourceMonth = sourceCalendar.get(Calendar.MONTH) + 1;

            long interval = currentMillis - sourceMillis;

            long seconds = TimeUnit.SECONDS.convert(interval, TimeUnit.MILLISECONDS);
            long minutes = TimeUnit.MINUTES.convert(interval, TimeUnit.MILLISECONDS);
            long hours = TimeUnit.HOURS.convert(interval, TimeUnit.MILLISECONDS);
            if(seconds < 60){
                if(currentDay == sourceDay){
                    friendlyTime = seconds + "秒前";
                }else{//source is: 23:59:40, current is: 00:00:03
                    friendlyTime = "昨天 " + sourceHour + ":" + sourceSecond;
                }
            }else if(minutes < 60){
                if(currentDay == sourceDay){
                    friendlyTime = minutes + "分钟前";
                }else{
                    friendlyTime = "昨天 " + sourceHour + ":" + sourceSecond;
                }
            }else if(hours < 24){
                if(currentDay == sourceDay){
                    friendlyTime = hours + "小时前";
                }else{
                    friendlyTime = "昨天 " + sourceHour + ":" + sourceSecond;
                }
            }else{
                if(currentYear == sourceYear){
                    return sourceMonth + "-" + sourceDay;
                }else{
                    return sourceYear + "-" + sourceMonth + "-" + sourceDay;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "time:" + (System.currentTimeMillis() - currentMillis));
        return friendlyTime;
    }
}
