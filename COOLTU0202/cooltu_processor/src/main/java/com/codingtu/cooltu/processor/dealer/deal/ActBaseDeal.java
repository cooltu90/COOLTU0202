package com.codingtu.cooltu.processor.dealer.deal;

import com.codingtu.cooltu.lib4j.data.data.JavaInfo;
import com.codingtu.cooltu.processor.annotation.activity.ActBase;
import com.codingtu.cooltu.processor.annotation.base.BaseClass;
import com.codingtu.cooltu.processor.dealer.builder.ActBaseBuilder;
import com.codingtu.cooltu.processor.dealer.deal.base.TypeBaseDeal;

import javax.lang.model.element.TypeElement;

public class ActBaseDeal extends TypeBaseDeal<ActBase> {
    @Override
    protected void dealTypeElement(String typeFullName, TypeElement te, ActBase annotation) {
        JavaInfo actJavaInfo = new JavaInfo(typeFullName);
        ActBaseBuilder actBaseBuilder = new ActBaseBuilder(actJavaInfo.name);
        actBaseBuilder.baseClass = te.getAnnotation(BaseClass.class);

    }
}
