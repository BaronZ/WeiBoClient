package com.zzb.weibo.widget.refreshlayout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzb.weibo.R;


/**
 * Created by ZZB on 2015/7/3 11:57
 */
public class CustomRefreshLayout extends SwipeRefreshLayout implements IRefreshLayout {
    private RefreshCallBack mCallBack;
    private View mEmptyView, mErrorView;
    private boolean mCanLoadMore = true;
    private boolean mIsRefreshing;

    public CustomRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public CustomRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        mEmptyView = LayoutInflater.from(context).inflate(R.layout.layout_empty_view, null);
        mEmptyView.setClickable(true);
        mErrorView = LayoutInflater.from(context).inflate(R.layout.layout_error_view, null);
        mErrorView.setClickable(true);
    }

    private void handleChildView() {
        View v = getChildAt(1);
        if (v instanceof RecyclerView) {
            RecyclerView rv = (RecyclerView) v;
            rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && !ViewCompat.canScrollVertically(rv, 1) && mCanLoadMore) {
                        mCallBack.onLoadMore();
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }

    }

    @Override
    public void manualRefresh() {
        post(() -> refresh());
//        setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
//        refresh();
    }

    @Override
    public void setCanLoadMore(boolean canLoadMore) {
        mCanLoadMore = canLoadMore;
    }


    @Override
    public void completeRefresh() {
        setRefreshing(false);
    }

    @Override
    public void completeLoadMore() {

    }

    @Override
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    @Override
    public void setEmptyText(String text) {
        if (!TextUtils.isEmpty(text)) {
            TextView tv = (TextView) mEmptyView.findViewById(R.id.tv_content);
            tv.setText(text);
        }
    }

    @Override
    public void setEmptyViewListener(OnClickListener listener) {
        if (mEmptyView != null) {
            mEmptyView.setOnClickListener(listener);
        }
    }

    @Override
    public void showEmptyView() {
        removePlaceHolderView();
        LinearLayout.LayoutParams pars = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        pars.gravity = Gravity.CENTER;

        ViewGroup vg = (ViewGroup) getParent();
        vg.removeView(mEmptyView);
        vg.addView(mEmptyView, pars);
    }

    @Override
    public void setErrorView(View errorView) {
        mErrorView = errorView;
    }

    @Override
    public void setErrorText(String text) {
        if (!TextUtils.isEmpty(text)) {
            TextView tv = (TextView) mErrorView.findViewById(R.id.tv_content);
            tv.setText(text);
        }
    }

    @Override
    public void setErrorViewListener(OnClickListener listener) {
        if (mErrorView != null) {
            mErrorView.setOnClickListener(listener);
        }
    }

    @Override
    public void showErrorView() {
        removePlaceHolderView();
        LinearLayout.LayoutParams pars = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        pars.gravity = Gravity.CENTER;
        addView(mErrorView, pars);
    }

    @Override
    public void setCallBack(RefreshCallBack callBack) {
        mCallBack = callBack;
        setOnRefreshListener(() -> refresh());

        handleChildView();
    }

    private void removePlaceHolderView() {
        ViewGroup vg = (ViewGroup) getParent();
        if (mErrorView != null) {
            vg.removeView(mErrorView);
        }
        if (mEmptyView != null) {
            vg.removeView(mEmptyView);
        }
    }

    private void refresh() {
        removePlaceHolderView();
        setRefreshing(true);
        mCallBack.onRefresh();
    }
//isRefreshing // TODO: 2015/9/19
}
