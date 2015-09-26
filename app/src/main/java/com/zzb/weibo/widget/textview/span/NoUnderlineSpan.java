package com.zzb.weibo.widget.textview.span;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * 去除TextView下划线
 * Created by sony on 2015/9/26.
 */
public abstract class NoUnderlineSpan extends ClickableSpan{
    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
