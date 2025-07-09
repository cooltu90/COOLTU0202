package com.codingtu.cooltu.processor.annotation.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Genericity {
    String name() default "";

    Class value() default Void.class;

    Class extendsClass() default Void.class;
}
