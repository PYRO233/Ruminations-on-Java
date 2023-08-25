package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.beans.factory.BeanFactory;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/24 23:51
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    private ApplicationContext parent;

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public ApplicationContext getParent() {
        return parent;
    }

    @Override
    public void setParent(ApplicationContext parent) {
        this.parent = parent;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return getParent();
    }

}
