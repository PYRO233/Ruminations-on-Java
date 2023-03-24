package com.github.pyro233.modern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: tao.zhou
 * @Date: 2023/3/24 14:08
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface JDK15 {

    String name() default "";

    String desc() default "";

}
