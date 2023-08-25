package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.beans.DefaultListableBeanFactory;
import com.github.pyro233.mini.spring.beans.factory.ListableBeanFactory;
import com.github.pyro233.mini.spring.beans.xml.XmlBeanDefinitionReader;
import com.github.pyro233.mini.spring.core.ClassPathXmlResource;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 23:02
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory defaultBeanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        defaultBeanFactory = new DefaultListableBeanFactory();
        final ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(fileName);
        final XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathXmlResource);
    }

    // method getBean
    @Override
    public Object getBean(String beanId) throws BeansException {
        return defaultBeanFactory.getBean(beanId);
    }

    @Override
    public ListableBeanFactory getBeanFactory() {
        return defaultBeanFactory;
    }
}
