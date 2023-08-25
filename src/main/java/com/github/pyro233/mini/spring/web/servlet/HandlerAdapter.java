package com.github.pyro233.mini.spring.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/25 8:36
 */
public interface HandlerAdapter {

    void handle(HttpServletRequest request, HttpServletResponse response, Object handler);

}
