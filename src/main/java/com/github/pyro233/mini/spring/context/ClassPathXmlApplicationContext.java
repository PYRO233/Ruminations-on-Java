package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.factory.BeanFactory;
import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.beans.SimpleAutowireCapableBeanFactory;
import com.github.pyro233.mini.spring.beans.xml.XmlBeanDefinitionReader;
import com.github.pyro233.mini.spring.core.ClassPathXmlResource;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 23:02
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private SimpleAutowireCapableBeanFactory simpleBeanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        simpleBeanFactory = new SimpleAutowireCapableBeanFactory();
        final ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(fileName);
        final XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathXmlResource);
    }

    // method getBean
    @Override
    public Object getBean(String beanId) throws BeansException {
        return simpleBeanFactory.getBean(beanId);
    }

    @Override
    public BeanFactory getBeanFactory() {
        return simpleBeanFactory;
    }
}
