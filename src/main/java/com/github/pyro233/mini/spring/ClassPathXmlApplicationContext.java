package com.github.pyro233.mini.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 23:02
 */
public class ClassPathXmlApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) {
        readXml(fileName);
        instanceBeans();
    }

    private void readXml(String fileName) {
        final SAXReader saxReader = new SAXReader();
        final URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        try {
            final Document document = saxReader.read(xmlPath);
            final Element rootElement = document.getRootElement();
            // 处理 <bean>
            for (Element element : rootElement.elements()) {
                final String beanId = element.attributeValue("id");
                final String beanClassName = element.attributeValue("class");
                final BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    // create method instanceBeans to create beans using Reflection
    public void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                final Class<?> clazz = Class.forName(beanDefinition.getClassName());
                // using Constructor.newInstance() to create instance
                final Object instance = clazz.newInstance();
                singletons.put(beanDefinition.getId(), instance);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    // method getBean
    public Object getBean(String beanId) {
        return singletons.get(beanId);
    }

}
