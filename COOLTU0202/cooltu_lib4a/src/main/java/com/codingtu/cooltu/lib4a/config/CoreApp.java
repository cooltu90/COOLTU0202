package com.codingtu.cooltu.lib4a.config;

import android.app.Application;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.log.Logs;

public abstract class CoreApp extends Application implements Thread.UncaughtExceptionHandler {

    //保存自己的实例
    public static CoreApp APP;

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
        CoreConfigs.configs(createConfigs());
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Logs.e(e);
    }

    public abstract CoreConfigs createConfigs();
}
