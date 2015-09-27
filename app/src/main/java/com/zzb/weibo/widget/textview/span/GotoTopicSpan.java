package com.zzb.weibo.widget.textview.span;

import android.view.View;

import com.zzb.weibo.activity.TopicActivity;

/**
 * 去话题页面
 * Created by sony on 2015/9/26.
 */
public class GotoTopicSpan extends NoUnderlineSpan {
    private String mTopic;

    public GotoTopicSpan(String topic){
        mTopic = topic;
    }
    @Override
    public void onClick(View widget) {
        TopicActivity.launch(widget.getContext(), mTopic);
    }
}
