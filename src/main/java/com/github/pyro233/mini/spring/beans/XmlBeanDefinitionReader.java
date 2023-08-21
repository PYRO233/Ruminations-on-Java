package com.github.pyro233.mini.spring.beans;

import com.github.pyro233.mini.spring.beans.config.CtorArg;
import com.github.pyro233.mini.spring.beans.config.BeanDefinition;
import com.github.pyro233.mini.spring.beans.config.CtorArgValues;
import com.github.pyro233.mini.spring.beans.config.PropertyValue;
import com.github.pyro233.mini.spring.beans.config.PropertyValues;
import com.github.pyro233.mini.spring.core.ClassPathXmlResource;
import org.dom4j.Element;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 19:59
 */
public class XmlBeanDefinitionReader {

    private SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    // method loadBeanDefinitions with argument Resource
    public void loadBeanDefinitions(ClassPathXmlResource resource) {
        while (resource.hasNext()) {
            final Element element = resource.next();
            final String beanId = element.attributeValue("id");
            final String beanClassName = element.attributeValue("class");
            final BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);

            beanDefinition.setConstructorArgumentValues(getCtorArgValues(element));
            beanDefinition.setPropertyValues(getPropertyValues(element));

            simpleBeanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }

    private CtorArgValues getCtorArgValues(Element element) {
        List<Element> constructorElements = element.elements("constructor-arg");
        CtorArgValues avs = new CtorArgValues();
        for (Element e : constructorElements) {
            String pType = e.attributeValue("type");
            String pName = e.attributeValue("name");
            String pValue = e.attributeValue("value");
            avs.addArgumentValue(new CtorArg(pType, pName, pValue));
        }
        return avs;
    }

    private PropertyValues getPropertyValues(Element element) {
        List<Element> propertyElements = element.elements("property");
        PropertyValues pvs = new PropertyValues();
        for (Element e : propertyElements) {
            String pType = e.attributeValue("type");
            String pName = e.attributeValue("name");
            String pValue = e.attributeValue("value");
            pvs.addPropertyValue(new PropertyValue(pType, pName, pValue));
        }
        return pvs;
    }

}
