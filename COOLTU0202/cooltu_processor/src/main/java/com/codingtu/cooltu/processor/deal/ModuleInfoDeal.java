package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.log.Logs;

import javax.lang.model.element.TypeElement;


public class ModuleInfoDeal extends TypeBaseDeal<ModuleInfo> {
    @Override
    protected void dealTypeElement(String typeFullName, TypeElement te, ModuleInfo moduleInfo) {
        Module.CURRENT  = moduleInfo.module();
        Pkg.R = moduleInfo.rPkg();

        Logs.i("module:"+Module.CURRENT);
        Logs.i("r:"+Pkg.R);

    }
}
