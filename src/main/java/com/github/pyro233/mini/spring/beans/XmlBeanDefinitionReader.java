package com.github.pyro233.mini.spring.beans;

import com.github.pyro233.mini.spring.core.ClassPathXmlResource;
import org.dom4j.Element;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 19:59
 */
public class XmlBeanDefinitionReader {

    private BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    // method loadBeanDefinitions with argument Resource
    public void loadBeanDefinitions(ClassPathXmlResource resource) {
        while (resource.hasNext()) {
            final Element element = resource.next();
            final String beanId = element.attributeValue("id");
            final String beanClassName = element.attributeValue("class");
            final BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            beanFactory.registerBeanDefinition(beanDefinition);
        }
    }

}
