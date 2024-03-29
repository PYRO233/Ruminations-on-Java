package com.github.pyro233.ood.ricksGuitars;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/5 9:57
 */
public class InstrumentSpec {

    private Map<String, Object> properties;

    public InstrumentSpec(Map<String, Object> properties) {
        if (properties == null) {
            this.properties = new HashMap<>();
        } else {
            this.properties = new HashMap<>(properties);
        }
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public boolean matches(InstrumentSpec otherSpec) {
        for (final String propertyName : otherSpec.getProperties().keySet()) {
            if (!properties.get(propertyName).equals(otherSpec.getProperty(propertyName))) {
                return false;
            }
        }
        return true;
    }

}
