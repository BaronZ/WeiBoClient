package com.zzb.weibo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zzb.mvp.BaseMVPActivity;
import com.zzb.weibo.R;
/**
 * 用户主页
 *@author ZZB
 *created at 2015/9/26 13:32
 */
public class UserActivity extends BaseMVPActivity {
    private static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    //跳转到当前activity
    public static void launch(Context context, String userName){
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(EXTRA_USERNAME, userName);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
