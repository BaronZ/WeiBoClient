package com.zzb.weibo.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zzb.library.utils.UrlUtils;
import com.zzb.weibo.R;
import com.zzb.weibo.http.base.HttpConfig;

import java.util.Map;

public class LoginAuthActivity extends BaseActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mWebView = $(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Map<String, String> params = UrlUtils.getUrlParams(url);
                String code = params.get("code");
                return true;
            }
        });

        mWebView.loadUrl(HttpConfig.AUTH_URL);
    }



    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }
}
