package com.codingtu.cooltu.processor.deal.base;

import com.codingtu.cooltu.lib4j.tool.OtherTool;
import com.codingtu.cooltu.processor.tool.ElementTool;

import java.lang.annotation.Annotation;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public abstract class TypeBaseDeal<T extends Annotation> extends BaseDeal {
    @Override
    public void dealElement(Element element) {
        TypeElement typeElement = (TypeElement) element;
        Class<T> annotationClass = OtherTool.getGenericity(this);
        T annotation = typeElement.getAnnotation(annotationClass);
        dealTypeElement(ElementTool.getType(typeElement), typeElement, annotation);
    }

    protected abstract void dealTypeElement(String typeFullName, TypeElement te, T annotation);

}
