package com.zzb.weibo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 我的首页相关
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageAdapter extends RecyclerView.Adapter{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch(viewType){
            case ViewType.NORMAL_WEIBO:break;
            case ViewType.FORWARD_WEIBO:break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private static interface ViewType{
        int NORMAL_WEIBO = 1;
        int FORWARD_WEIBO = 2;

    }
}
