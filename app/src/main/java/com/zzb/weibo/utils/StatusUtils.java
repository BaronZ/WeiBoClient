package com.zzb.weibo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微博内容工具类
 * Created by sony on 2015/9/26.
 */
public class StatusUtils {
    private static final String REGEXP_AT = "@[\\u4e00-\\u9fa5\\w\\-]+";
    private static final String REGEXP_TAG = "#[^#]+#";
    private static final Pattern PATTERN_AT = Pattern.compile(REGEXP_AT);
    private static final Pattern PATTERN_TAG = Pattern.compile(REGEXP_TAG);
    private static final String URL_AT = "https://api.weibo.com/2/statuses/mentions.json";
    private static final String URL_TAG = "https://api.weibo.com/2/search/topics.json";
    /**
     * 格式化@和话题##
     *@author ZZB
     *created at 2015/9/26 0:21
     */
    public static String formatAtTag(String status) {
        if(status.contains("@")){
            Matcher mAt = PATTERN_AT.matcher(status);
            while (mAt.find()) {
                try {
                    String at = mAt.group();
                    status = status.replaceAll(at, "<a href='@'>" + at.substring(1) + "</a>");
                } catch (Exception e) {
                    continue;
                }
            }
        }
        if(status.contains("#")){
            Matcher m = PATTERN_TAG.matcher(status);
            while (m.find()) {
                try {
                    String tag = m.group();
                    status = status.replaceAll(tag, "<a href='#'>" + tag.substring(1, tag.length() - 1) + "</a>");
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return status;
    }
}
