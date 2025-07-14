package com.codingtu.cooltu.lib4a.tool;

import android.os.Handler;
import android.os.Looper;

public class HandlerTool {
    private static Handler mainHandler;

    public static Handler getMainHandler() {
        if (mainHandler == null)
            mainHandler = new Handler(Looper.getMainLooper());
        return mainHandler;
    }

    public static void repeatTask(int times, long distenceTime, RepeatTask task) {
        Handler mainHandler = getMainHandler();
        for (int i = 0; i < times; i++) {
            int finalI = i;
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    task.run(finalI);
                }
            }, distenceTime * i);
        }
    }

    public static interface RepeatTask {
        void run(int index);
    }
}
