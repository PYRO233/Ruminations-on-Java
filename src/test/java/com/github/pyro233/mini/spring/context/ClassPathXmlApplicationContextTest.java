package com.github.pyro233.mini.spring.context;

import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.test.Service;
import com.github.pyro233.mini.spring.test.ServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/18 23:42
 */
class ClassPathXmlApplicationContextTest {

    @Test
    public void test_parseXml_and_getBean() throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final Object testService = context.getBean("testGetBean");
        assertTrue(testService instanceof Service);
    }

    @Test
    public void test_parseXml_and_set_values() throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final Object testService = context.getBean("testValues");
        // testService cast to AService
        final ServiceImpl aService = (ServiceImpl) testService;
        // assert name == abc
        assertEquals("abc", aService.getName());
        assertEquals(3, aService.getLevel());
        assertEquals("Hello World!", aService.getProperty());

        final ServiceImpl testServiceWithDefaultCtor =  (ServiceImpl) context.getBean("testValues");
        assertEquals("Hello World!", testServiceWithDefaultCtor.getProperty());
    }

    @Test
    public void test_parseXml_and_dependencyInjection() throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        final Service testDependency = (Service) context.getBean("testDependency");
        assertEquals("L2", testDependency.getData());
    }
}