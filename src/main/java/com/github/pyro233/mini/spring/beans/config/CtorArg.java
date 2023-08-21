package com.github.pyro233.mini.spring.beans.config;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/21 15:48
 */
public class CtorArg {
    private Object value;
    private String type;
    private String name;

    public CtorArg(String type, String name, Object value) {
        this.value = value;
        this.type = type;
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
