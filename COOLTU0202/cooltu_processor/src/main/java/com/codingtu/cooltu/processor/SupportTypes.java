package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.activity.ActBase;

public class SupportTypes {

    public static BaseEs<Class> types() {
        BaseEs<Class> es = Es.es();
        es.add(ModuleInfo.class);
        es.add(ActBase.class);
        return es;
    }

}
