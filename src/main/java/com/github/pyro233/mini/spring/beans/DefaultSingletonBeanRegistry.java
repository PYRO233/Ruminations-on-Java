package com.github.pyro233.mini.spring.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/20 9:31
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletons.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

}
