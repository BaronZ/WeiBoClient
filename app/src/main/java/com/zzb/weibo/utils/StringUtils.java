package com.zzb.weibo.utils;

/**
 * 字符串工具类
 * Created by ZZB on 2015/9/20.
 */
public class StringUtils {
    /**
     * 如果最后以chars结尾，删除它
     *@author ZZB
     *created at 2015/9/10 11:55
     */
    public static StringBuilder deleteLastChars(StringBuilder sb, String chars){
        int sbLen = sb.length(), charsLen = chars.length();
        if(sb.lastIndexOf(chars) > 0) {
            sb.delete(sbLen - charsLen, sbLen);
        }
        return sb;
    }
}
