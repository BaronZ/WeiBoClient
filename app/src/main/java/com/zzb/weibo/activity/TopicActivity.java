package com.zzb.weibo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzb.weibo.R;
/**
 * 话题
 *@author ZZB
 *created at 2015/9/26 13:42
 */
public class TopicActivity extends AppCompatActivity {
    private static final String EXTRA_TOPIC = "EXTRA_TOPIC";

    public static void launch(Context context, String topic){
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra(EXTRA_TOPIC, topic);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
    }
}
