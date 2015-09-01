package com.zzb.weibo.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zzb.library.utils.UrlUtils;
import com.zzb.weibo.R;
import com.zzb.weibo.common.Config;

import java.util.Map;

public class LoginAuthActivity extends BaseActivity {
    private WebView mWebView;
    private static final String AUTH_URL = "https://api.weibo.com/oauth2/authorize?client_id=" + Config.APP_KEY + "&response_type=code&redirect_uri=" + Config.REDIRECT_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mWebView = $(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("code", url);
                Map<String, String> params = UrlUtils.getUrlParams(url);
                String code = params.get("code");
                Log.e("code", code);
                return true;
            }
        });

        mWebView.loadUrl(AUTH_URL);
    }



    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }
}
