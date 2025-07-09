package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;

import javax.lang.model.element.TypeElement;


public class ModuleInfoDeal extends TypeBaseDeal<ModuleInfo> {
    @Override
    protected void dealTypeElement(String typeFullName, TypeElement te, ModuleInfo annotation) {

    }
}
