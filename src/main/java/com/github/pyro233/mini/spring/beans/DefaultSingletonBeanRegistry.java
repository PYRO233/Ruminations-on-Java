package com.github.pyro233.mini.spring.beans;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/20 9:31
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return Optional.ofNullable(singletonObjects.get(beanName)).orElse(earlySingletonObjects.get(beanName));
    }

    public void registerEarlySingleton(String beanName, Object singletonObject) {
        earlySingletonObjects.put(beanName, singletonObject);
    }

}
