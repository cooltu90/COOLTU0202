package com.codingtu.cooltu.lib4a.uicore;

import android.app.Activity;

public interface CoreFragmentInterface {

    void toast(String msg);

    Activity getAct();

    void onCreateComplete();

}
