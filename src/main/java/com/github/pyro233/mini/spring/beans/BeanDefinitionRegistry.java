package com.github.pyro233.mini.spring.beans;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/21 15:16
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition bd);

}
