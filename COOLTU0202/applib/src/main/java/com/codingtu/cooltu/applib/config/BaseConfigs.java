package com.codingtu.cooltu.applib.config;

import com.codingtu.cooltu.applib.BuildConfig;
import com.codingtu.cooltu.applib.constant.Constants;
import com.codingtu.cooltu.applib.ui.page.base.BaseActivityInAppLib;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.lib4a.config.CoreConfigs;
import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;

@ModuleInfo(
        module = Module.APP_LIB,
        rPkg = Constants.PKG_MODULE_APP_LIB,
        baseActivity = BaseActivityInAppLib.class
)
public abstract class BaseConfigs extends CoreConfigs {

    public static BaseConfigs configs() {
        return (BaseConfigs) LibConfigs.configs();
    }

    @Override
    public boolean isLog() {
        return BuildConfig.IS_LOG;
    }

}
