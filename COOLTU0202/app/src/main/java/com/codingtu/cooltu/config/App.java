package com.codingtu.cooltu.config;

import com.codingtu.cooltu.applib.config.BaseApp;
import com.codingtu.cooltu.lib4a.config.CoreConfigs;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;

public class App extends BaseApp {
    @Override
    public CoreConfigs createConfigs() {
        return new Configs();
    }
}
