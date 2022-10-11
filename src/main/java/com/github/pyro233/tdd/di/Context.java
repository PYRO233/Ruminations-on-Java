package com.github.pyro233.tdd.di;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/11 0:43
 */
public class Context {

    private Map<Class<?>, Object> components = new HashMap<>();
    private Map<Class<?>, Class<?>> componentImplementation = new HashMap<>();

    public <ComponentType> void bind(final Class<ComponentType> type, final ComponentType instance) {
        components.put(type, instance);
    }

    public <ComponentType, ComponentImpl extends ComponentType> void bind(final Class<ComponentType> type,
                                                                          final Class<ComponentImpl> implementation) {
        componentImplementation.put(type, implementation);
    }

    public <ComponentType> ComponentType get(final Class<ComponentType> type) {
        if (components.containsKey(type))
            return (ComponentType) components.get(type);
        Class<?> implementation = componentImplementation.get(type);
        try {
            return (ComponentType) implementation.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
