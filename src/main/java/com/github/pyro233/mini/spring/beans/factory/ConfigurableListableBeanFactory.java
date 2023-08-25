package com.github.pyro233.mini.spring.beans.factory;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/25 1:13
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, HierarchicalBeanFactory {

    void setParentBeanFactory(BeanFactory parentBeanFactory);

}
