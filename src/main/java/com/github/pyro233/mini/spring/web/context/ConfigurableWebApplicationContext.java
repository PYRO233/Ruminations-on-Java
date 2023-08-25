package com.github.pyro233.mini.spring.web.context;

import com.github.pyro233.mini.spring.context.ConfigurableApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/23 14:03
 */
public interface ConfigurableWebApplicationContext extends WebApplicationContext, ConfigurableApplicationContext {

    void setServletContext(ServletContext servletContext);

}
