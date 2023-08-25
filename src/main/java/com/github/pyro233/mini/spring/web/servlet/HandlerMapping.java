package com.github.pyro233.mini.spring.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/25 8:39
 */
public interface HandlerMapping {

    HandlerMethod getHandler(HttpServletRequest request);

}
