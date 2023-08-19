package com.github.pyro233.mini.spring.beans;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 9:57
 */
public interface BeanFactory {

    // method getBean
    Object getBean(String name) throws NoSuchBeanDefinitionException;

    // method registerBeanDefinition
    void registerBeanDefinition(BeanDefinition beanDefinition);

}
