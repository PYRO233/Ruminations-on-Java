package com.github.pyro233.mini.spring.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 23:57
 */
public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();
    private List<String> beanNames = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        Object singleton = singletons.get(beanName);
        if (singleton == null) {
            final int i = beanNames.indexOf(beanName);
            if (i == -1) {
                throw new NoSuchBeanDefinitionException();
            } else {
                BeanDefinition bd = beanDefinitions.get(i);
                try {
                    singleton = Class.forName(bd.getClassName()).newInstance();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                singletons.put(bd.getId(), singleton);
            }
        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.add(beanDefinition);
        beanNames.add(beanDefinition.getId());
    }
}
