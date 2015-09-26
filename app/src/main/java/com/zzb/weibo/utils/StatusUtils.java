package com.zzb.weibo.utils;

import android.text.SpannableString;

import com.zzb.weibo.widget.textview.span.GotoAtSapn;
import com.zzb.weibo.widget.textview.span.GotoTopicSpan;
import com.zzb.weibo.widget.textview.span.GotoWebViewSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微博内容工具类
 * Created by sony on 2015/9/26.
 */
public class StatusUtils {
    private static final String REGEXP_AT = "@[\\u4e00-\\u9fa5\\w\\-]+";
    private static final String REGEXP_TAG = "#[^#]+#";
    private static final String REGEXP_URL = "http://t.cn/[\\w]+";
    private static final Pattern PATTERN_AT = Pattern.compile(REGEXP_AT);
    private static final Pattern PATTERN_TAG = Pattern.compile(REGEXP_TAG);
    private static final Pattern PATTERN_URL = Pattern.compile(REGEXP_URL);
    private static final String URL_AT = "https://api.weibo.com/2/statuses/mentions.json";
    private static final String URL_TAG = "https://api.weibo.com/2/search/topics.json";


    public static SpannableString getFormattedSpan(String text){
        SpannableString spannableString = new SpannableString(text);
        if(text.contains("@")){
            Matcher mAt = PATTERN_AT.matcher(text);
            while (mAt.find()) {
                try {
                    String at = mAt.group();
                    spannableString.setSpan(new GotoAtSapn(at), mAt.start(), mAt.end(), 0);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        if(text.contains("#")){
            Matcher mTag = PATTERN_TAG.matcher(text);
            while (mTag.find()) {
                try {
                    String tag = mTag.group();
                    spannableString.setSpan(new GotoTopicSpan(tag), mTag.start(), mTag.end(), 0);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        if(text.contains("http://t.cn/")){
            Matcher m = PATTERN_URL.matcher(text);
            while (m.find()) {
                try {
                    String url = m.group();
                    spannableString.setSpan(new GotoWebViewSpan(url), m.start(), m.end(), 0);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return spannableString;
    }

}
