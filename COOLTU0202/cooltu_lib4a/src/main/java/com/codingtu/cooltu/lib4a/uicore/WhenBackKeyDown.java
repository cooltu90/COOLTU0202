package com.codingtu.cooltu.lib4a.uicore;

import android.view.KeyEvent;

public abstract class WhenBackKeyDown implements WhenKeyDown {
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return onBack(event);
        }
        return false;
    }

    public abstract boolean onBack(KeyEvent event);
}
