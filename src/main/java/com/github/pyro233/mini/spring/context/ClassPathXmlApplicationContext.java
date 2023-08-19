package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.BeanDefinition;
import com.github.pyro233.mini.spring.beans.BeanFactory;
import com.github.pyro233.mini.spring.beans.NoSuchBeanDefinitionException;
import com.github.pyro233.mini.spring.beans.SimpleBeanFactory;
import com.github.pyro233.mini.spring.beans.XmlBeanDefinitionReader;
import com.github.pyro233.mini.spring.core.ClassPathXmlResource;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 23:02
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        beanFactory = new SimpleBeanFactory();
        final ClassPathXmlResource classPathXmlResource = new ClassPathXmlResource(fileName);
        final XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathXmlResource);
    }

    // method getBean
    public Object getBean(String beanId) throws NoSuchBeanDefinitionException {
        return beanFactory.getBean(beanId);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }

}
