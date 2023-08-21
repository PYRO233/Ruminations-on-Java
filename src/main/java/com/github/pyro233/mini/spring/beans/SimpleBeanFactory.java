package com.github.pyro233.mini.spring.beans;

import com.github.pyro233.mini.spring.beans.config.CtorArg;
import com.github.pyro233.mini.spring.beans.config.BeanDefinition;
import com.github.pyro233.mini.spring.beans.config.CtorArgValues;
import com.github.pyro233.mini.spring.beans.config.PropertyValue;
import com.github.pyro233.mini.spring.beans.config.PropertyValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 既是 Factory 也是 Registry
 *
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
            final BeanDefinition bd;
            try {
                bd = getBeanDefinition(beanName);
                singleton = createBean(bd);
                registerSingleton(bd.getId(), singleton);
            } catch (NoSuchBeanDefinitionException e) {
                throw new BeansException();
            }
        }
        return singleton;
    }
    // endregion BeanFactory

    // region BeanFactory
    @Override
    public void registerBeanDefinition(String name, BeanDefinition bd) {
        beanDefinitionMap.put(name, bd);
    }
    // endregion BeanFactory

    private Object createBean(BeanDefinition bd) throws BeansException {
        Object obj;
        final ReflectionArgs ctorArgs = getCtorArgs(bd.getConstructorArgumentValues());

        try {
            final Class<?> clz = Class.forName(bd.getClassName());
            obj = doCreateBean(ctorArgs, clz);

            super.registerEarlySingleton(bd.getId(), obj);

            handleProperties(bd, obj, clz);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            throw new BeansException();
        }

        return obj;
    }

    private Object doCreateBean(ReflectionArgs ctorArgs, Class<?> clz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final Constructor<?> ctor = clz.getConstructor(ctorArgs.paramTypes());
        return ctor.newInstance(ctorArgs.paramValues());
    }

    record ReflectionArgs(Class<?>[] paramTypes, Object[] paramValues) {
    }

    private void handleProperties(BeanDefinition bd, Object obj, Class<?> clz) {
        final PropertyValues propertyValues = bd.getPropertyValues();

        for (int i = 0; i < propertyValues.size(); i++) {
            PropertyValue propertyValue = propertyValues.get(i);
            String pName = propertyValue.getName();
            String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
            ReflectionArgs reflectionArgs = getSetPropertyArg(propertyValue);
            try {
                Method method = clz.getMethod(methodName, reflectionArgs.paramTypes());
                method.invoke(obj, reflectionArgs.paramValues());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private ReflectionArgs getSetPropertyArg(PropertyValue propertyValue) {
        String pType = propertyValue.getType();
        Object pValue = propertyValue.getValue();

        Class<?>[] paramTypes = new Class<?>[1];
        Object[] paramValues = new Object[1];

        if (!propertyValue.isRef()) {
            if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                paramTypes[0] = String.class;
            } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                paramTypes[0] = Integer.class;
            } else if ("int".equals(pType)) {
                paramTypes[0] = int.class;
            } else {
                paramTypes[0] = String.class;
            }
            paramValues[0] = pValue;
        } else {
            try {
                paramTypes[0] = Class.forName(pType);
                paramValues[0] = getBean((String) pValue);
            } catch (BeansException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ReflectionArgs(paramTypes, paramValues);
    }

    private ReflectionArgs getCtorArgs(final CtorArgValues ctorArgValues) {
        final int ctorArgsCount = ctorArgValues.getArgumentCount();
        Class<?>[] paramTypes = new Class<?>[ctorArgsCount];
        Object[] paramValues = new Object[ctorArgsCount];

        for (int i = 0; i < ctorArgsCount; i++) {
            CtorArg argumentValue = ctorArgValues.getIndexedArgumentValue(i);
            final String type = argumentValue.getType();

            if ("String".equals(type) || "java.lang.String".equals(type)) {
                paramTypes[i] = String.class;
                paramValues[i] = argumentValue.getValue();
            } else if ("Integer".equals(type) || "java.lang.Integer".equals(type)) {
                paramTypes[i] = Integer.class;
                paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
            } else if ("int".equals(type)) {
                paramTypes[i] = int.class;
                paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
            } else {
                paramTypes[i] = String.class;
                paramValues[i] = argumentValue.getValue();
            }
        }
        return new ReflectionArgs(paramTypes, paramValues);
    }


    private BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        final BeanDefinition bd = beanDefinitionMap.get(beanName);
        if (bd == null) {
            throw new NoSuchBeanDefinitionException();
        }
        return bd;
    }
}
