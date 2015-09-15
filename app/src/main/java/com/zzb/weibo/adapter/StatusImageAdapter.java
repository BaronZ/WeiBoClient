package com.zzb.weibo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zzb.library.utils.ListUtils;
import com.zzb.weibo.model.ImageUrl;

import java.util.List;

/**
 * Created by ZZB on 2015/9/15.
 */
public class StatusImageAdapter extends RecyclerView.Adapter<StatusImageAdapter.ViewHolder> {

    private List<ImageUrl> mUrls;
    private Activity mActivity;
    public StatusImageAdapter(Activity activity){
        //用Activity做参数，Glide能更好地控制生命周期
        mActivity = activity;
    }
    public void setUrls(List<ImageUrl> urls){
        mUrls = urls;
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
        ImageView iv = (ImageView) holder.itemView;
        Glide.with(mActivity).load(url).into(iv);
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
