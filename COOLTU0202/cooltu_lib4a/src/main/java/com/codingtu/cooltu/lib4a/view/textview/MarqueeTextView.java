package com.codingtu.cooltu.lib4a.view.textview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4a.view.base.CoreTextView;

public class MarqueeTextView extends CoreTextView {
    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        this.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.setSingleLine(true);
        this.setMarqueeRepeatLimit(-1);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
