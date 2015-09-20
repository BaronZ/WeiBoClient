package com.zzb.weibo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
    private boolean mCanLoadMore;

    public MyHomePageAdapter() {
        SCREEN_SIZE = DisplayUtils.getScreenWidth();
        ROW_HEIGHT = SCREEN_SIZE / 3;
    }

    public void setData(List<Status> data) {
        mData = data;
    }
    public void addData(List<Status> data){
        mData.addAll(data);
    }
    //这里设置是否可加载更多，可加载更多，则显示progressbar, 不可加载更多显示文字
    public void setCanLoadMore(boolean canLoadMore){
        mCanLoadMore = canLoadMore;
        notifyItemChanged(getItemCount() - 1);
    }
    public long getLastStatusId(){
        int lastIndex = ListUtils.getSize(mData) - 1;
        return lastIndex > 0 ? getItemId(lastIndex) : 0;
    }

    @Override
    public long getItemId(int position) {
        return ListUtils.isEmpty(mData) ? 0 : mData.get(position).id;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        NormalViewHolder holder = null;
        View itemView = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case ViewType.NORMAL_TEXT_WEIBO:
                itemView = inflater.inflate(R.layout.rv_normal_text_weibo, parent, false);
                holder = new NormalViewHolder(itemView);
                break;
            case ViewType.NORMAL_PIC_1_WEIBO:
            case ViewType.NORMAL_PIC_3_WEIBO:
            case ViewType.NORMAL_PIC_6_WEIBO:
            case ViewType.NORMAL_PIC_9_WEIBO:
                itemView = inflater.inflate(R.layout.rv_normal_pics_weibo, parent, false);
                holder = new NormalViewHolder(itemView);
                GridLayoutManager normalManager = new GridLayoutManager(context, getRvSpanNum(viewType));
                holder.mRvPics.setLayoutManager(normalManager);
                StatusImageAdapter normalAdapter = new StatusImageAdapter((Activity) context);
                holder.mRvPics.setAdapter(normalAdapter);
                int rvHeight = getRvHeight(viewType);
                LinearLayout.LayoutParams nLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, rvHeight);
                holder.mRvPics.setLayoutParams(nLp);
                break;
            case ViewType.FORWARD_TEXT_WEIBO:
                itemView = inflater.inflate(R.layout.rv_repost_text_weibo, parent, false);
                holder = new ForwardViewHolder(itemView);
                break;
            case ViewType.FORWARD_PIC_1_WEIBO:
            case ViewType.FORWARD_PIC_3_WEIBO:
            case ViewType.FORWARD_PIC_6_WEIBO:
            case ViewType.FORWARD_PIC_9_WEIBO:
                itemView = inflater.inflate(R.layout.rv_repost_pics_weibo, parent, false);
                holder = new ForwardViewHolder(itemView);
                GridLayoutManager forwardManager = new GridLayoutManager(context, getRvSpanNum(viewType));
                StatusImageAdapter forwardAdapter = new StatusImageAdapter((Activity) context);
                holder.mRvPics.setLayoutManager(forwardManager);
                holder.mRvPics.setAdapter(forwardAdapter);
                int fRvHeight = getRvHeight(viewType);
                LinearLayout.LayoutParams fLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, fRvHeight);
                holder.mRvPics.setLayoutParams(fLp);
                break;
            case ViewType.FOOTER_VIEW_MSG:
                TextView tv = new TextView(context);
                tv.setText("没数据咯");//TODO 拿到外面去设置
                itemView = tv;
                holder = new NormalViewHolder(itemView, true);
                break;
            case ViewType.FOOTER_VEW_PROGRESS:
                itemView = new ProgressBar(context);
                holder = new NormalViewHolder(itemView, true);
                break;
        }
        return holder;
    }

    //根据ViewType获取RecyclerView列数
    private int getRvSpanNum(int viewType) {
        switch (viewType) {
            case ViewType.NORMAL_PIC_1_WEIBO:
            case ViewType.FORWARD_PIC_1_WEIBO:
                return 2;
            default:
                return 3;
        }
    }

    //根据ViewType获取RecyclerView的高度
    private int getRvHeight(int viewType) {
        int rvHeight = 0;
        switch (viewType) {
            case ViewType.NORMAL_PIC_1_WEIBO:
            case ViewType.NORMAL_PIC_3_WEIBO:
            case ViewType.FORWARD_PIC_1_WEIBO:
            case ViewType.FORWARD_PIC_3_WEIBO:
                rvHeight = ROW_HEIGHT;
                break;
            case ViewType.NORMAL_PIC_6_WEIBO:
            case ViewType.FORWARD_PIC_6_WEIBO:
                rvHeight = 2 * ROW_HEIGHT;
                break;
            case ViewType.NORMAL_PIC_9_WEIBO:
            case ViewType.FORWARD_PIC_9_WEIBO:
                rvHeight = 3 * ROW_HEIGHT;
                break;
        }

        return rvHeight;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(isFooterView(position)){
            return;
        }
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
            case ViewType.NORMAL_PIC_1_WEIBO:
            case ViewType.NORMAL_PIC_3_WEIBO:
            case ViewType.NORMAL_PIC_6_WEIBO:
            case ViewType.NORMAL_PIC_9_WEIBO:
                StatusImageAdapter nAdapter = (StatusImageAdapter) baseViewHolder.mRvPics.getAdapter();
                nAdapter.setUrls(status.picUrls);
                nAdapter.notifyDataSetChanged();
                NormalViewHolder nPicHolder = (NormalViewHolder) viewHolder;
                break;
            case ViewType.FORWARD_TEXT_WEIBO:
                ForwardViewHolder fTextHolder = (ForwardViewHolder) viewHolder;
                break;
            case ViewType.FORWARD_PIC_1_WEIBO:
            case ViewType.FORWARD_PIC_3_WEIBO:
            case ViewType.FORWARD_PIC_6_WEIBO:
            case ViewType.FORWARD_PIC_9_WEIBO:
                StatusImageAdapter fAdapter = (StatusImageAdapter) baseViewHolder.mRvPics.getAdapter();
                fAdapter.setUrls(retweetedStatus.picUrls);
                fAdapter.notifyDataSetChanged();
                ForwardViewHolder fPicHolder = (ForwardViewHolder) viewHolder;
                break;
        }
    }

    private void populateBaseViewHolder(int position, NormalViewHolder holder) {
        Status status = mData.get(position);
        holder.mTvUserName.setText(status.user.name);
        holder.mTvTime.setText(status.getFriendlyTime());
        holder.mTvStatus.setText(status.text);
        holder.mTvFrom.setText(Html.fromHtml("来自: " + status.source));
        Context context = holder.mIvIcon.getContext();
        Picasso.with(context).load(status.user.avatarLarge).into(holder.mIvIcon);

    }

    @Override
    public int getItemCount() {
        int size = ListUtils.getSize(mData);
        return size == 0 ? 0 : size + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(isFooterView(position)){
            return mCanLoadMore ? ViewType.FOOTER_VEW_PROGRESS : ViewType.FOOTER_VIEW_MSG;
        }
        Status status = mData.get(position);
        int viewType = -1;
        if (status.retweetedStatus != null) {//转发微博
            int imgNum = ListUtils.getSize(status.retweetedStatus.picUrls);
            if (imgNum == 0) {//无图
                viewType = ViewType.FORWARD_TEXT_WEIBO;
            } else if (imgNum == 1) {//单图
                viewType = ViewType.FORWARD_PIC_1_WEIBO;
            } else if (imgNum > 1 && imgNum < 4) {//2-3图
                viewType = ViewType.FORWARD_PIC_3_WEIBO;
            } else if (imgNum > 3 && imgNum < 7) {// 3-6图
                viewType = ViewType.FORWARD_PIC_6_WEIBO;
            } else if (imgNum > 6 && imgNum < 10) {//7-9图
                viewType = ViewType.FORWARD_PIC_9_WEIBO;
            }
        } else {
            int imgNum = ListUtils.getSize(status.picUrls);
            if (imgNum == 0) {//无图
                viewType = ViewType.NORMAL_TEXT_WEIBO;
            } else if (imgNum == 1) {//单图
                viewType = ViewType.NORMAL_PIC_1_WEIBO;
            } else if (imgNum > 1 && imgNum < 4) {//2-3图
                viewType = ViewType.NORMAL_PIC_3_WEIBO;
            } else if (imgNum > 3 && imgNum < 7) {// 3-6图
                viewType = ViewType.NORMAL_PIC_6_WEIBO;
            } else if (imgNum > 6 && imgNum < 10) {//7-9图
                viewType = ViewType.NORMAL_PIC_9_WEIBO;
            }
        }
        return viewType;
    }

    private static class NormalViewHolder extends RecyclerView.ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);
            initView();
        }
        public NormalViewHolder(View itemView, boolean isFooter){
            super(itemView);
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
            mIvIcon = $(R.id.iv_icon);
            mTvFrom.setMovementMethod(LinkMovementMethod.getInstance());
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
        int NORMAL_PIC_1_WEIBO = 2;//单图
        int NORMAL_PIC_3_WEIBO = 3;//2-3图
        int NORMAL_PIC_6_WEIBO = 4;//4-6图
        int NORMAL_PIC_9_WEIBO = 5;//7-9图
        int FORWARD_TEXT_WEIBO = 6;
        int FORWARD_PIC_1_WEIBO = 7;
        int FORWARD_PIC_3_WEIBO = 8;
        int FORWARD_PIC_6_WEIBO = 9;
        int FORWARD_PIC_9_WEIBO = 10;
        int FOOTER_VEW_PROGRESS = 11;//底部的View
        int FOOTER_VIEW_MSG = 12;
    }

    private boolean isFooterView(int position){
        return position == getItemCount() - 1;
    }
}
