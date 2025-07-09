package com.codingtu.cooltu.config;

import com.codingtu.cooltu.applib.config.BaseConfigs;
import com.codingtu.cooltu.constant.Constants;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;

@ModuleInfo(
        module = Module.APP,
        rPkg = Constants.PKG_MODULE_APP
)
public class Configs extends BaseConfigs {

    public static Configs configs() {
        return (Configs) LibConfigs.configs();
    }

}
