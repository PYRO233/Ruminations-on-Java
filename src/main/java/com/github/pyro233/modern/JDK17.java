package com.github.pyro233.modern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/6 12:15
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface JDK17 {

    String name() default "";

    String desc() default "";

}
