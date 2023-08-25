package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.factory.HierarchicalBeanFactory;
import com.github.pyro233.mini.spring.beans.factory.ListableBeanFactory;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/23 13:10
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory {

    ListableBeanFactory getBeanFactory();

    ApplicationContext getParent();

}
