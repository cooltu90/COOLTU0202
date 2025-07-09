package com.codingtu.cooltu.lib4j.config;

import com.codingtu.cooltu.lib4j.json.base.JsonHolder;
import com.codingtu.cooltu.lib4j.json.fastjson.FastJsonHolder;

public abstract class LibConfigs {

    private static LibConfigs CONFIGS;

    public static void configs(LibConfigs configs) {
        CONFIGS = configs;
    }

    public static LibConfigs configs() {
        return CONFIGS;
    }

    public boolean isLogJsonException() {
        return false;
    }

    public JsonHolder createJsonHolder() {
        return new FastJsonHolder();
    }

    public String getDefaultLogTag() {
        return "TestApp";
    }

    public abstract boolean isLog();

    public abstract void baseLog(int level, String tag, String msg);
}
