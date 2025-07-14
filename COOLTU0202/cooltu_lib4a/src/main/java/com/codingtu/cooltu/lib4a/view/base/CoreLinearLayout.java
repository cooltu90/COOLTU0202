package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class CoreLinearLayout extends LinearLayout implements OnDestroy {

    public CoreLinearLayout(Context context) {
        this(context, null);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);

    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    public void onDestroy() {

    }
}
