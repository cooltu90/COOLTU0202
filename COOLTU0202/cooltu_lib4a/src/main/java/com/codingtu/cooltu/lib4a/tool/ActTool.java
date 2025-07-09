package com.codingtu.cooltu.lib4a.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import com.codingtu.cooltu.lib4a.R;

/********************************
 *
 * 跟activity有关的工具类
 *
 ********************************/

public class ActTool {

    public static final int ANIM_RIGHT_IN = 0;
    public static final int ANIM_FADE_IN = 1;

    /********************************
     *
     * activity跳转动画
     *
     ********************************/
    private static void overridePendingTransition(Activity act, int enterAnim, int exitAnim) {
        act.overridePendingTransition(enterAnim, exitAnim);
    }

    /********************************
     *
     * 从右进入的动画
     *
     ********************************/
    public static void actRightIn(Activity act) {
        overridePendingTransition(act, R.anim.translatex100to0, R.anim.translatex0tof100);
    }

    /********************************
     *
     * 从右出去的动画
     *
     ********************************/
    public static void actRightOut(Activity act) {
        overridePendingTransition(act, R.anim.translatexf100to0, R.anim.translatex0to100);
    }

    public static void actFadeIn(Activity act) {
        overridePendingTransition(act, R.anim.alpha0to1, R.anim.alpha1to1);
    }

    public static void actFadeOut(Activity act) {
        overridePendingTransition(act, R.anim.alpha1to1, R.anim.alpha1to0);
    }

    /**************************************************
     *
     * 启动activity
     *
     **************************************************/

//    public static final void startActivity(Activity act, Class<? extends Activity> aClass) {
//        startActivity(act, new Intent(act, aClass));
//    }
//
//    public static final void startActivityForResult(Activity act, Class<? extends Activity> aClass,
//                                                    int requestCode) {
//        startActivityForResult(act, new Intent(act, aClass), requestCode);
//    }
//
//    public static final void startActivity(Activity act, Intent intent) {
//        act.startActivity(intent);
//        ActFunc.actRightIn(act);
//    }
    public static final void startActivity(Activity act, Class<? extends Activity> clazz) {
        act.startActivity(new Intent(act, clazz));
        ActTool.actRightIn(act);
    }


    public static final void startActivityForResult(Activity act, Intent intent, int requestCode) {
        act.startActivityForResult(intent, requestCode);
        ActTool.actRightIn(act);
    }

//    public static final void startActivityForResultFadeIn(Activity act, Intent intent, int requestCode) {
//        act.startActivityForResult(intent, requestCode);
//        ActFunc.actFadeIn(act);
//    }

    @SuppressLint("SourceLockedOrientationActivity")
    public static void sreenForceShuPing(Activity act) {
        act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    public static void sreenForceHengPing(Activity act) {
        act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
