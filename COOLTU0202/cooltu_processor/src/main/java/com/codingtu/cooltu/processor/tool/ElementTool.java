package com.codingtu.cooltu.processor.tool;

import javax.lang.model.element.Element;

public class ElementTool {
    public static String getType(Element e) {
        return e.asType().toString();
    }
}
