package com.github.pyro233.mini.spring.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 既是 Factory 也是 Registry
 * @Author: tao.zhou
 * @Date: 2023/8/19 23:57
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    // region BeanFactory
    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = getSingleton(beanName);
        if (singleton == null) {
            try {
                final BeanDefinition bd = getBeanDefinition(beanName);
                singleton = Class.forName(bd.getClassName()).newInstance();
                registerSingleton(bd.getId(), singleton);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchBeanDefinitionException e) {
                throw new BeansException();
            }
        }
        return singleton;
    }

    private BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        final BeanDefinition bd = beanDefinitionMap.get(beanName);
        if (bd == null) {
            throw new NoSuchBeanDefinitionException();
        }
        return bd;
    }
    // endregion BeanFactory

    // region BeanFactory
    @Override
    public void registerBeanDefinition(String name, BeanDefinition bd) {
        beanDefinitionMap.put(name, bd);
    }
    // endregion BeanFactory
}
