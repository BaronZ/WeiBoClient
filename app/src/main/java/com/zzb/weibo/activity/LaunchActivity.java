package com.zzb.weibo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zzb.weibo.R;
import com.zzb.weibo.data.AccessTokenKeeper;
import com.zzb.weibo.model.AccessToken;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        AccessToken token = AccessTokenKeeper.readAccessToken(this);
        if(token.isValid()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, LoginAuthActivity.class);
            startActivity(intent);
        }

    }



    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }
}
