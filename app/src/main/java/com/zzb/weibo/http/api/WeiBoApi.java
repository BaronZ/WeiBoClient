package com.zzb.weibo.http.api;

import com.zzb.weibo.http.base.ApiUrl;
import com.zzb.weibo.model.Status;
import com.zzb.weibo.model.StatusList;

import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;

/**h
 * 微博api
 * Created by ZZB on 2015/9/6.
 */
public interface WeiBoApi {


    /**
     * 获取当前登录用户及其所关注用户的微博，lastId之前count条微博
     * @param lastId 若指定此参数，则返回ID小于或等于lastId的微博(即比lastId早的微博)，传入0获取最新微博。
     * @param count 加载微博的条数
     *created at 2015/9/6 14:26
     */
    @GET(ApiUrl.WeiBo.FRIENDS_TIMELINE)
    Observable<StatusList> getFriendsTimeLine(@Query("max_id")long lastId, @Query("count")int count);

    /**
     * 上传带图片的微博
     * @param status 要发布的微博文本内容，必须做URLencode，内容不超过140个汉字。
     * @param typedFile 要上传的图片，仅支持JPEG、GIF、PNG格式，图片大小小于5M。new TypedFile("multipart/form-data", new File("path"));
     *@author ZZB
     *created at 2015/9/7 22:16
     */
    @Multipart
    @POST(ApiUrl.WeiBo.POST_WEIBO_WITH_PIC)
    void postWeiboWithPic(@Part("status")String status, @Part("pic")TypedFile typedFile, retrofit.Callback<Status> callback);
    //还有其他的分组可见，密友可见，发布的地理位置
}
