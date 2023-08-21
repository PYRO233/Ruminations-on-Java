package com.github.pyro233.mini.spring.beans.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/21 15:45
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    // method get(int)
    public PropertyValue get(int index) {
        return propertyValueList.get(index);
    }

    // method size
    public int size() {
        return propertyValueList.size();
    }
}
