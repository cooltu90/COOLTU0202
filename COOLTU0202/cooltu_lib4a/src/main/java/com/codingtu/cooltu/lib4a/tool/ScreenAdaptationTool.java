package com.codingtu.cooltu.lib4a.tool;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.codingtu.cooltu.lib4a.config.CoreApp;
import com.codingtu.cooltu.lib4a.config.CoreConfigs;

public class ScreenAdaptationTool {

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    //通过设计稿获取尺寸方法：测量的尺寸*360/设计稿宽度
    public static void setCustomDensity(Context context) {
        DisplayMetrics appDisplayMetrics = CoreApp.APP.getResources().getDisplayMetrics();

        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            CoreApp.APP.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = CoreApp.APP.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        float targetDensity = ((float) appDisplayMetrics.widthPixels) / CoreConfigs.configs().getDensity();
        float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = context.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;

    }

}
