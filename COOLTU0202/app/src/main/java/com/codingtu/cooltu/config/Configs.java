package com.codingtu.cooltu.config;

import com.codingtu.cooltu.applib.config.BaseConfigs;
import com.codingtu.cooltu.lib4j.config.LibConfigs;

public class Configs extends BaseConfigs {

    public static Configs configs() {
        return (Configs) LibConfigs.configs();
    }

}
