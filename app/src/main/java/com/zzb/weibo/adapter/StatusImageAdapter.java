package com.zzb.weibo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zzb.library.utils.DisplayUtils;
import com.zzb.library.utils.ListUtils;
import com.zzb.weibo.model.ImageUrl;

import java.util.List;

/**
 * 微博图片
 * Created by ZZB on 2015/9/15.
 */
public class StatusImageAdapter extends RecyclerView.Adapter<StatusImageAdapter.ViewHolder> {

    private List<ImageUrl> mUrls;
    private Activity mActivity;
    private static int IMAGE_SIZE;
    public StatusImageAdapter(Activity activity){
        //用Activity做参数，Glide能更好地控制生命周期
        mActivity = activity;
    }
    public void setUrls(List<ImageUrl> urls){
        mUrls = urls;
        int screenWidth = DisplayUtils.getScreenWidth();
        int imageNum = urls.size();
        if(imageNum == 1){
            IMAGE_SIZE = screenWidth / 2;
        }else{
            IMAGE_SIZE = screenWidth / 3;
        }
    }
    @Override
    public StatusImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        ViewHolder holder = new ViewHolder(imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(StatusImageAdapter.ViewHolder holder, int position) {
        String url = mUrls.get(position).getMiddleUrl();
//        String url = mUrls.get(position).thumbUrl;
        ImageView iv = (ImageView) holder.itemView;
//        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        Glide.with(mActivity).load(url).override(IMAGE_SIZE, IMAGE_SIZE).crossFade().into(iv);
        Picasso.with(mActivity).load(url).resize(IMAGE_SIZE, IMAGE_SIZE).centerCrop().into(iv);
    }

    @Override
    public int getItemCount() {
        return ListUtils.getSize(mUrls);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
