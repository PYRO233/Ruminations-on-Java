package com.github.pyro233.mini.spring.beans.factory;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/25 0:54
 */
public interface ListableBeanFactory extends BeanFactory {

    String[] getBeanDefinitionNames();

}
