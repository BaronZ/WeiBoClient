package com.zzb.weibo.common;

/**
 * 图片工具类
 * Created by ZZB on 2015/9/15.
 */
public class ImageUtils {
    public static final String TAG_IMG_SIZE_THUMB = "thumbnail";
    public static final String TAG_IMAGE_SIZE_MIDDLE = "bmiddle";
    public static final String TAG_IMAGE_SIZE_LARGE = "large";

    /**
     * @param thumbnailUrl 新浪微博返回的缩略图片地址
     * @return 中图地址
     */
    public static String getMiddleUrl(String thumbnailUrl){
        return thumbnailUrl.replace(TAG_IMG_SIZE_THUMB, TAG_IMAGE_SIZE_MIDDLE);
    }

    /**
     * @param thumbnailUrl 新浪微博返回的缩略图片地址
     * @return 大图地址
     */
    public static String getLargeUrl(String thumbnailUrl){
        return thumbnailUrl.replace(TAG_IMG_SIZE_THUMB, TAG_IMAGE_SIZE_LARGE);
    }
}
