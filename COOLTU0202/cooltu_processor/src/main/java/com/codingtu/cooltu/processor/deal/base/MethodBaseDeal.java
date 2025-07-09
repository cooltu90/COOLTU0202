package com.codingtu.cooltu.processor.deal.base;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

public abstract class MethodBaseDeal extends BaseDeal {

    @Override
    public void dealElement(Element element) {
        dealMethodElement((ExecutableElement) element);
    }

    protected abstract void dealMethodElement(ExecutableElement ee);
}
