package com.zzb.weibo.widget.textview.span;

import android.view.View;

import com.zzb.weibo.activity.WebViewActivity;

/**
 * 去WebView页面
 * Created by sony on 2015/9/26.
 */
public class GotoWebViewSpan extends NoUnderlineSpan {
    private String mUrl;
    public GotoWebViewSpan(String url){
        mUrl = url;
    }
    @Override
    public void onClick(View widget) {
        WebViewActivity.launch(widget.getContext(), mUrl);
    }
}
