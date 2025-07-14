package com.codingtu.cooltu.lib4a.tool;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.codingtu.cooltu.lib4a.config.CoreApp;


public class ResourceTool {

    private static Context getContext() {
        return CoreApp.APP;
    }

    public static int getDimen(int resourceId) {
        return getDimen(getContext(), resourceId);
    }

    public static int getDimen(Context context, int resourceId) {
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public static int getColor(int resourceId) {
        return getColor(getContext(), resourceId);
    }

    private static int getColor(Context context, int resourceId) {
        return context.getResources().getColor(resourceId);
    }

    public static Drawable getDrawable(Context context, int drawableId) {
        return context.getResources().getDrawable(drawableId);
    }

    public static Drawable getDrawable(int drawableId) {
        return getDrawable(getContext(), drawableId);
    }
}
