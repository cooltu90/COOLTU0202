package com.codingtu.cooltu.lib4a.tool;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class ViewTool {


    /****************************************************************
     *
     * 获得rootView
     *
     ****************************************************************/

    public static View getRootView(Activity act) {
        return ((ViewGroup) act.getWindow().getDecorView().findViewById(android.R.id.content))
                .getChildAt(0);
    }

    public static ViewGroup getRootViewGroup(Activity act) {
        return (ViewGroup) getRootView(act);
    }
}
