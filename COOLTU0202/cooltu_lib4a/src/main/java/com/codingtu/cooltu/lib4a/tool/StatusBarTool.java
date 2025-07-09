package com.codingtu.cooltu.lib4a.tool;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarTool {

    public static final int STATUS_BAR_MODE_DARK = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    public static final int STATUS_BAR_MODE_LIGHT = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

    public static void fullScreen(Activity act) {
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        act.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public static void translucentAndLight(Activity act) {
        setStatusBarTranslucentAndMode(act, STATUS_BAR_MODE_LIGHT);
    }

    public static void translucentAndDark(Activity act) {
        setStatusBarTranslucentAndMode(act, STATUS_BAR_MODE_DARK);
    }

    private static void setStatusBarTranslucentAndMode(Activity act, int mode) {
        if (canUseTranslucentStatus()) {
            Window window = act.getWindow();
            window.clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(mode);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//6.0的真机上反而是用这句生效
        }
    }

    public static boolean canUseTranslucentStatus() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
