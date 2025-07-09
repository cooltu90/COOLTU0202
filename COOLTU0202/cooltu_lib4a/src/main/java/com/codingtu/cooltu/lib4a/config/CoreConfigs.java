package com.codingtu.cooltu.lib4a.config;

import android.util.Log;

import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.lib4j.log.LibLogs;

public abstract class CoreConfigs extends LibConfigs {

    public static CoreConfigs configs() {
        return (CoreConfigs) LibConfigs.configs();
    }

    @Override
    public void baseLog(int level, String tag, String msg) {
        switch (level) {
            case LibLogs.LEVEL_INFO:
                Log.i(tag, msg);
                break;
            case LibLogs.LEVEL_WARNING:
                Log.w(tag, msg);
                break;
            case LibLogs.LEVEL_ERROR:
                Log.e(tag, msg);
                break;
        }
    }

}
