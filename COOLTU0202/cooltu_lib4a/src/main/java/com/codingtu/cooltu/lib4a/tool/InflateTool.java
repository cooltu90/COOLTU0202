package com.codingtu.cooltu.lib4a.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.config.CoreApp;


/**
 * 布局文件转换成view
 */

public class InflateTool {
    public static View inflate(int layoutId) {
        return inflate(layoutId, null, false);
    }

    public static View inflate(Context context, int layoutId) {
        return inflate(context, layoutId, null, false);
    }

    public static View inflate(int layoutId, ViewGroup root) {
        return inflate(layoutId, root, false);
    }

    public static View inflate(Context context, int layoutId, ViewGroup root) {
        return inflate(context, layoutId, root, false);
    }

    public static View inflate(int layoutId, ViewGroup root, boolean attachToRoot) {
        return inflate(CoreApp.APP, layoutId, root, attachToRoot);
    }

    public static View inflate(Context context, int layoutId, ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(layoutId, root, attachToRoot);
    }


    public static View inflate(LayoutInflater inflater, int layoutId, ViewGroup root) {
        return inflate(inflater, layoutId, root, false);
    }

    public static View inflate(LayoutInflater inflater, int layoutId, ViewGroup root,
                               boolean attachToRoot) {
        return inflater.inflate(layoutId, root, attachToRoot);
    }

}
