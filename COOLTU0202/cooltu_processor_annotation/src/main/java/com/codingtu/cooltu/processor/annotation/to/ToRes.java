package com.codingtu.cooltu.processor.annotation.to;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**************************************************
 *
 * 没有任何操作。只是用于点击可以跳转
 *
 **************************************************/
@Retention(RetentionPolicy.SOURCE)
public @interface ToRes {
    int[] value() default {};
}