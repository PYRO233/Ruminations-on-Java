package com.github.pyro233.mini.spring.web.context;

import com.github.pyro233.mini.spring.context.AbstractApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/25 0:10
 */
public abstract class AbstractWebApplicationContext extends AbstractApplicationContext implements ConfigurableWebApplicationContext {

    ServletContext servletContext;

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
