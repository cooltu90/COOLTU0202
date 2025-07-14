package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class CoreEditText extends androidx.appcompat.widget.AppCompatEditText implements OnDestroy {

    public CoreEditText(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public CoreEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CoreEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    public void onDestroy() {

    }
}
