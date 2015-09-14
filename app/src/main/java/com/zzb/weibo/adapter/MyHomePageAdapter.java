package com.zzb.weibo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzb.library.utils.ListUtils;
import com.zzb.weibo.R;
import com.zzb.weibo.model.Status;

import java.util.List;

/**
 * 我的首页相关
 * Created by ZZB on 2015/9/8.
 */
public class MyHomePageAdapter extends RecyclerView.Adapter {

    private List<Status> mData;

    public void setData(List<Status> data) {
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View itemView = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ViewType.NORMAL_TEXT_WEIBO:
                itemView = inflater.inflate(R.layout.rv_normal_text_weibo, parent, false);
                holder = new NormalViewHolder(itemView);
                break;
            case ViewType.NORMAL_PICS_WEIBO:
                itemView = inflater.inflate(R.layout.rv_normal_pics_weibo, parent, false);
                holder = new NormalViewHolder(itemView);
                break;
            case ViewType.FORWARD_TEXT_WEIBO:
                itemView = inflater.inflate(R.layout.rv_repost_text_weibo, parent, false);
                holder = new ForwardViewHolder(itemView);
                break;
            case ViewType.FORWARD_PICS_WEIBO:
                itemView = inflater.inflate(R.layout.rv_repost_pics_weibo, parent, false);
                holder = new ForwardViewHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        NormalViewHolder baseViewHolder = (NormalViewHolder) viewHolder;
        populateBaseViewHolder(position, baseViewHolder);
        switch (viewType) {
            case ViewType.NORMAL_TEXT_WEIBO:
                NormalViewHolder nTextHolder = (NormalViewHolder) viewHolder;
                break;
            case ViewType.NORMAL_PICS_WEIBO:
                NormalViewHolder nPicHolder = (NormalViewHolder) viewHolder;
                break;
            case ViewType.FORWARD_TEXT_WEIBO:
                ForwardViewHolder fTextHolder = (ForwardViewHolder) viewHolder;
                break;
            case ViewType.FORWARD_PICS_WEIBO:
                ForwardViewHolder fPicHolder = (ForwardViewHolder) viewHolder;
                break;
        }
    }

    private void populateBaseViewHolder(int position, NormalViewHolder baseViewHolder) {
        Status status = mData.get(position);

        baseViewHolder.mTvUserName.setText(status.user.name);
        baseViewHolder.mTvTime.setText(status.createdAt);
//        baseViewHolder.mTvStatus.setText();
    }

    @Override
    public int getItemCount() {
        return ListUtils.getSize(mData);
    }

    @Override
    public int getItemViewType(int position) {
//        Status status = mData.get(position);
//        if(status.retweetedStatus != null){
//            return status.retweetedStatus.originalPic == null ? ViewType.NORMAL_TEXT_WEIBO : ViewType.NORMAL_PICS_WEIBO;
//        }else{
//            return status.originalPic == null ? ViewType.NORMAL_TEXT_WEIBO : ViewType.NORMAL_PICS_WEIBO;
//        }
        return ViewType.NORMAL_PICS_WEIBO;
    }

    private static class NormalViewHolder extends RecyclerView.ViewHolder{
        public NormalViewHolder(View itemView) {
            super(itemView);
            initView();
        }
        protected <T extends View> T $(int id){
            return (T) itemView.findViewById(id);
        }
        void initView(){
            mTvUserName = $(R.id.tv_user_name);
            mTvTime = $(R.id.tv_time);
            mTvFrom = $(R.id.tv_from);
            mTvStatus = $(R.id.tv_status);
        }
        TextView mTvUserName, mTvTime, mTvFrom, mTvStatus, mTvComment, mTvForward, mTvGood;
        ImageView mIvIcon;
        RecyclerView mRvPics;


    }

    private static class ForwardViewHolder extends NormalViewHolder{
        TextView mTvOrgUserName, mTvOrgTime, mTvOrgFrom, mTvOrgStatus;
        public ForwardViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static interface ViewType {
        int NORMAL_TEXT_WEIBO = 1;
        int NORMAL_PICS_WEIBO = 2;
        int FORWARD_TEXT_WEIBO = 3;
        int FORWARD_PICS_WEIBO = 4;
    }
}
