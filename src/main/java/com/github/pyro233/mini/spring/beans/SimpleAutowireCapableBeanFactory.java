package com.github.pyro233.mini.spring.beans;

import com.github.pyro233.mini.spring.beans.annotation.Autowired;
import com.github.pyro233.mini.spring.beans.config.BeanDefinition;
import com.github.pyro233.mini.spring.beans.factory.AutowireCapableBeanFactory;
import com.github.pyro233.mini.spring.core.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 既是 Factory 也是 Registry
 *
 * @Author: tao.zhou
 * @Date: 2023/8/19 23:57
 */
public class SimpleAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    Object initializeBean(final String beanName, final Object bean, final BeanDefinition bd) throws BeansException {
        Object wrappedBean = bean;
        wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
        if (StringUtils.isNotEmpty(bd.getInitMethodName())) {
            invokeInitMethod(bd, bean);
        }
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }


    public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) throws BeansException {
        final Class<?> clazz = bean.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            final boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                final String autowiredBeanName = field.getName();
                final Object autowiredBean = getBean(autowiredBeanName);
                field.setAccessible(true);
                try {
                    field.set(bean, autowiredBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    private void invokeInitMethod(BeanDefinition bd, Object obj) {
        Class<?> clz = obj.getClass();
        try {
            Method method = clz.getMethod(bd.getInitMethodName());
            method.invoke(obj);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
