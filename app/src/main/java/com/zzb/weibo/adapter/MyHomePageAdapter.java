package com.zzb.weibo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzb.library.utils.DisplayUtils;
import com.zzb.library.utils.ListUtils;
import com.zzb.weibo.R;
import com.zzb.weibo.model.Status;

import java.util.List;

/**
 * 我的首页相关
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = MyHomePageAdapter.class.getSimpleName();
    private List<Status> mData;
    private static int SCREEN_SIZE;
    private static int ROW_HEIGHT;

    public MyHomePageAdapter(){
        SCREEN_SIZE = DisplayUtils.getScreenWidth();
        ROW_HEIGHT = SCREEN_SIZE / 3;
    }
    public void setData(List<Status> data) {
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        NormalViewHolder holder = null;
        View itemView = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case ViewType.NORMAL_TEXT_WEIBO:
                Log.d(TAG, "onCreateViewHolder NORMAL_TEXT_WEIBO");
                itemView = inflater.inflate(R.layout.rv_normal_text_weibo, parent, false);
                holder = new NormalViewHolder(itemView);
                break;
            case ViewType.NORMAL_PICS_WEIBO:
            case ViewType.NORMAL_PIC_WEIBO:
                Log.d(TAG, "onCreateViewHolder NORMAL_PICS_WEIBO");
                itemView = inflater.inflate(R.layout.rv_normal_pics_weibo, parent, false);
                holder = new NormalViewHolder(itemView);
                GridLayoutManager normalManager = new GridLayoutManager(context, 3);
                holder.mRvPics.setLayoutManager(normalManager);
                StatusImageAdapter normalAdapter = new StatusImageAdapter((Activity) context);
                holder.mRvPics.setAdapter(normalAdapter);
                break;
            case ViewType.FORWARD_TEXT_WEIBO:
                Log.d(TAG, "onCreateViewHolder FORWARD_TEXT_WEIBO");
                itemView = inflater.inflate(R.layout.rv_repost_text_weibo, parent, false);
                holder = new ForwardViewHolder(itemView);
//                holder.mRvPics.setVisibility(View.GONE);
                break;
            case ViewType.FORWARD_PICS_WEIBO:
            case ViewType.FORWARD_PIC_WEIBO:
                Log.d(TAG, "onCreateViewHolder FORWARD_PICS_WEIBO");
                itemView = inflater.inflate(R.layout.rv_repost_pics_weibo, parent, false);
                holder = new ForwardViewHolder(itemView);
                GridLayoutManager forwardManager = new GridLayoutManager(context, 3);
                StatusImageAdapter forwardAdapter = new StatusImageAdapter((Activity) context);
                holder.mRvPics.setLayoutManager(forwardManager);
                holder.mRvPics.setAdapter(forwardAdapter);
                break;
        }
        return holder;
    }
    private int getRvHeight(int imgNum){
        int rvHeight = 0;
        if(imgNum <= 3){
            rvHeight = ROW_HEIGHT;
        }else if(imgNum <= 6){
            rvHeight = 2 * ROW_HEIGHT;
        }else{
            rvHeight = 3 * ROW_HEIGHT;
        }
        return rvHeight;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        NormalViewHolder baseViewHolder = (NormalViewHolder) viewHolder;
        populateBaseViewHolder(position, baseViewHolder);
        Status status = mData.get(position);
        Status retweetedStatus = status.retweetedStatus;
        baseViewHolder.mTvStatus.setText(status.text);
        switch (viewType) {
            case ViewType.NORMAL_TEXT_WEIBO:
                NormalViewHolder nTextHolder = (NormalViewHolder) viewHolder;

                break;
            case ViewType.NORMAL_PICS_WEIBO:
            case ViewType.NORMAL_PIC_WEIBO:
                StatusImageAdapter nAdapter = (StatusImageAdapter) baseViewHolder.mRvPics.getAdapter();
                nAdapter.setUrls(status.picUrls);
                nAdapter.notifyDataSetChanged();
                NormalViewHolder nPicHolder = (NormalViewHolder) viewHolder;
                int rvHeight = getRvHeight(ListUtils.getSize(status.picUrls));
                LinearLayout.LayoutParams nLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, rvHeight);
                baseViewHolder.mRvPics.setLayoutParams(nLp);
                break;
            case ViewType.FORWARD_TEXT_WEIBO:
                ForwardViewHolder fTextHolder = (ForwardViewHolder) viewHolder;
                break;
            case ViewType.FORWARD_PICS_WEIBO:
            case ViewType.FORWARD_PIC_WEIBO:
                StatusImageAdapter fAdapter = (StatusImageAdapter) baseViewHolder.mRvPics.getAdapter();
                fAdapter.setUrls(retweetedStatus.picUrls);
                fAdapter.notifyDataSetChanged();
                ForwardViewHolder fPicHolder = (ForwardViewHolder) viewHolder;
                int fRvHeight = getRvHeight(ListUtils.getSize(retweetedStatus.picUrls));
                LinearLayout.LayoutParams fLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, fRvHeight);
                baseViewHolder.mRvPics.setLayoutParams(fLp);
                break;
        }
    }

    private void populateBaseViewHolder(int position, NormalViewHolder baseViewHolder) {
        Status status = mData.get(position);
        baseViewHolder.mTvUserName.setText(status.user.name);
        baseViewHolder.mTvTime.setText(status.getFriendlyTime());
//        baseViewHolder.mTvStatus.setText();
    }

    @Override
    public int getItemCount() {
        return ListUtils.getSize(mData);
    }

    @Override
    public int getItemViewType(int position) {
        Status status = mData.get(position);
        int viewType = -1;
        if (status.retweetedStatus != null) {//转发微博
            int imgNum = ListUtils.getSize(status.retweetedStatus.picUrls);
            if (imgNum == 0) {//无图
                viewType = ViewType.FORWARD_TEXT_WEIBO;
            } else if (imgNum == 1) {//单图
                viewType = ViewType.FORWARD_PIC_WEIBO;
            } else {//多图
                viewType = ViewType.FORWARD_PICS_WEIBO;
            }
        } else {
            int imgNum = ListUtils.getSize(status.picUrls);
            if (imgNum == 0) {
                viewType = ViewType.NORMAL_TEXT_WEIBO;
            } else if (imgNum == 1) {
                viewType = ViewType.NORMAL_PIC_WEIBO;
            } else {
                viewType = ViewType.NORMAL_PICS_WEIBO;
            }
        }
        return viewType;
    }

    private static class NormalViewHolder extends RecyclerView.ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        protected <T extends View> T $(int id) {
            return (T) itemView.findViewById(id);
        }

        void initView() {
            mTvUserName = $(R.id.tv_user_name);
            mTvTime = $(R.id.tv_time);
            mTvFrom = $(R.id.tv_from);
            mTvStatus = $(R.id.tv_status);
            mRvPics = $(R.id.rv_pics);
        }

        TextView mTvUserName, mTvTime, mTvFrom, mTvStatus, mTvComment, mTvForward, mTvGood;
        ImageView mIvIcon;
        RecyclerView mRvPics;
    }

    private static class ForwardViewHolder extends NormalViewHolder {
        TextView mTvOrgUserName, mTvOrgTime, mTvOrgFrom, mTvOrgStatus;

        public ForwardViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static interface ViewType {
        int NORMAL_TEXT_WEIBO = 1;
        int NORMAL_PIC_WEIBO = 2;//单图
        int NORMAL_PICS_WEIBO = 3;//多图
        int FORWARD_TEXT_WEIBO = 4;
        int FORWARD_PIC_WEIBO = 5;
        int FORWARD_PICS_WEIBO = 6;
    }
}
