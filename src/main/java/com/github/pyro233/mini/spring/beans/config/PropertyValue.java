package com.github.pyro233.mini.spring.beans.config;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/21 15:49
 */
public class PropertyValue {

    private final String type;
    private final String name;
    private final Object value;

    public PropertyValue(String type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

}
