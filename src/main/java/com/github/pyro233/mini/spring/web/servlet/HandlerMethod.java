package com.github.pyro233.mini.spring.web.servlet;

import java.lang.reflect.Method;

/**
 * 封装反射相关属性，如要支持 Post 方法还需要扩充属性。
 *
 * @Author: tao.zhou
 * @Date: 2023/8/25 8:39
 */
public class HandlerMethod {

    private final Object bean;

    private final Method method;

    public HandlerMethod(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }
}
