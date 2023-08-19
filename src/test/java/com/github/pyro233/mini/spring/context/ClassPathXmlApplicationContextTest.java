package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.NoSuchBeanDefinitionException;
import com.github.pyro233.mini.spring.test.AService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 23:42
 */
class ClassPathXmlApplicationContextTest {

    @Test
    public void test_parseXml_and_getBean() throws NoSuchBeanDefinitionException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final Object testService = context.getBean("aservice");
        assertTrue(testService instanceof AService);
    }

}