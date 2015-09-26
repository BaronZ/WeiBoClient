package com.zzb.weibo.widget.textview.span;

import android.view.View;

import com.zzb.weibo.activity.UserActivity;

/**
 * 去到@用户主页
 * Created by sony on 2015/9/26.
 */
public class GotoAtSapn extends NoUnderlineSpan {
    private String mUserName;
    public GotoAtSapn(String userName){
        mUserName = userName;
    }
    @Override
    public void onClick(View widget) {
        UserActivity.to(widget.getContext(), mUserName);
    }
}
