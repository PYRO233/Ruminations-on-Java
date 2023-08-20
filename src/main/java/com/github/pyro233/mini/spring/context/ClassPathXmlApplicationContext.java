package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.BeanFactory;
import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.beans.SimpleBeanFactory;
import com.github.pyro233.mini.spring.beans.XmlBeanDefinitionReader;
import com.github.pyro233.mini.spring.core.ClassPathXmlResource;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 23:02
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private SimpleBeanFactory simpleBeanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        simpleBeanFactory = new SimpleBeanFactory();
        final ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(fileName);
        final XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathXmlResource);
    }

    // method getBean
    public Object getBean(String beanId) throws BeansException {
        return simpleBeanFactory.getBean(beanId);
    }

}
