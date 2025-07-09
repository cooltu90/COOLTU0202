package com.codingtu.cooltu.applib.config;

import com.codingtu.cooltu.applib.BuildConfig;
import com.codingtu.cooltu.lib4a.config.CoreConfigs;
import com.codingtu.cooltu.lib4j.config.LibConfigs;

public abstract class BaseConfigs extends CoreConfigs {

    public static BaseConfigs configs() {
        return (BaseConfigs) LibConfigs.configs();
    }

    @Override
    public boolean isLog() {
        return BuildConfig.IS_LOG;
    }

}
