package com.github.pyro233.mini.spring.web;

import com.github.pyro233.mini.spring.web.context.XmlWebApplicationContext;
import com.github.pyro233.mini.spring.web.context.ConfigurableWebApplicationContext;
import com.github.pyro233.mini.spring.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 配到 web.xml 中，或者使用 Servlet3.0 的 SpringServletContainerInitializer 动态注册。
 *
 * @Author: tao.zhou
 * @Date: 2023/8/23 13:08
 */
public class ContextLoaderListener implements ServletContextListener {

    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initWebApplicationContext(servletContextEvent.getServletContext());
    }

    private void initWebApplicationContext(ServletContext servletContext) {
        final String initParameter = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        ConfigurableWebApplicationContext cwac = new XmlWebApplicationContext(initParameter);
        cwac.setServletContext(servletContext);
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, cwac);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
