package com.github.pyro233.mini.spring.beans.factory;

import com.github.pyro233.mini.spring.beans.BeansException;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/22 11:06
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException;

}
