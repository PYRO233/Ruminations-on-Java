package com.github.pyro233.mini.spring.web.context;

import com.github.pyro233.mini.spring.context.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/23 13:18
 */
public class XmlWebApplicationContext extends ClassPathXmlApplicationContext implements ConfigurableWebApplicationContext {

    ServletContext servletContext;

    public XmlWebApplicationContext(String fileName) {
        super(fileName);
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
