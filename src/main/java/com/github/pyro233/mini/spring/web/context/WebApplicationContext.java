package com.github.pyro233.mini.spring.web.context;

import com.github.pyro233.mini.spring.context.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/23 13:10
 */
public interface WebApplicationContext extends ApplicationContext {

    String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class.getName() + ".ROOT";

    ServletContext getServletContext();

}
