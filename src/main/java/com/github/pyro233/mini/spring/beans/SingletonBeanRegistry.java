package com.github.pyro233.mini.spring.beans;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/20 9:27
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

}
