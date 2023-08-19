package com.github.pyro233.mini.spring;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 22:59
 */
public class BeanDefinition {

    private String id;

    private String className;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
