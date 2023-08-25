package com.github.pyro233.mini.spring.web.servlet;

import com.github.pyro233.mini.spring.web.XmlScanComponentHelper;
import com.github.pyro233.mini.spring.web.context.AnnotationConfigWebApplicationContext;
import com.github.pyro233.mini.spring.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 未经过测试（不想引入 Tomcat）
 * @Author: tao.zhou
 * @Date: 2023/8/22 19:50
 */
public class DispatcherServlet extends HttpServlet {

    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";

    private WebApplicationContext webApplicationContext;
    private WebApplicationContext parentApplicationContext;
    private HandlerMapping handlerMapping;
    private HandlerAdapter handlerAdapter;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // (没实现，不想引入 web.xml)
        parentApplicationContext = (WebApplicationContext) servletConfig.getServletContext()
                                                                        .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        // ServletConfig 和 ServletContext initParameter 不一样
        try {
            final URL xmlPath = servletConfig.getServletContext().getResource(servletConfig.getInitParameter(CONFIG_LOCATION_PARAM));
            List<String> packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
            webApplicationContext = new AnnotationConfigWebApplicationContext(packageNames, parentApplicationContext);
            refresh();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        super.init(servletConfig);
    }

    /**
     * handlerMethod = handlerMapping.getHandler(httpServletRequest);
     * handlerAdapter.handle(httpServletRequest, httpServletResponse, handlerMethod);
     */
    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final HandlerMethod handlerMethod = handlerMapping.getHandler(httpServletRequest);
        handlerAdapter.handle(httpServletRequest, httpServletResponse, handlerMethod);
    }

    private void refresh() {
        // version 1: parse xml and register bean to mappingObjs... (没实现)
        // version 2: component scan (由 HandlerMapping 完成)
        handlerMapping = new RequestMappingHandlerMapping(webApplicationContext);
        handlerAdapter = new RequestMappingHandlerAdapter();
    }

}
