package com.codingtu.cooltu.lib4a.tool;

import android.content.Context;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class DestoryTool {

    public static void onDestory(Context context, OnDestroy onDestroy) {
        if (context instanceof Destroys) {
            ((Destroys) context).addOnDestroy(onDestroy);
        }
    }

}
