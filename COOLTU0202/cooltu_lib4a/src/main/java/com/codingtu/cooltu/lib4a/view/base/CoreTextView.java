package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class CoreTextView extends androidx.appcompat.widget.AppCompatTextView implements OnDestroy {

    public CoreTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    public void onDestroy() {

    }
}
