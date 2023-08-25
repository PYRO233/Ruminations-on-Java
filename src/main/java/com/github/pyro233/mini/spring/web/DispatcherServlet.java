package com.github.pyro233.mini.spring.web;

import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.web.context.AnnotationConfigWebApplicationContext;
import com.github.pyro233.mini.spring.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 未经过测试（不想引入 Tomcat）
 * @Author: tao.zhou
 * @Date: 2023/8/22 19:50
 */
public class DispatcherServlet extends HttpServlet {

    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";

    private WebApplicationContext webApplicationContext;
    private WebApplicationContext parentApplicationContext;

    private String contextConfigLocation;

    private Map<String, ControllerInstance> controllerInstances = new HashMap<>();
    private List<String> controllerNames = new ArrayList<>();

    // mapping: uri -> class, method
    private Map<String, ControllerMethod> mappingObjsMethods = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // (没实现，不想引入 web.xml)
        parentApplicationContext = (WebApplicationContext) servletConfig.getServletContext()
                                                                        .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        // ServletConfig 和 ServletContext initParameter 不一样
        contextConfigLocation = servletConfig.getInitParameter(CONFIG_LOCATION_PARAM);
        try {
            final URL xmlPath = servletConfig.getServletContext().getResource(contextConfigLocation);
            List<String> packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
            webApplicationContext = new AnnotationConfigWebApplicationContext(packageNames, parentApplicationContext);
            refresh();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        super.init(servletConfig);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String sPath = httpServletRequest.getServletPath();
        final ControllerMethod controllerMethod = mappingObjsMethods.get(sPath);
        if (controllerMethod == null) {
            httpServletResponse.getWriter()
                               .write("404 Not Found");
            return;
        }
        final Object obj = controllerMethod.obj();
        final Method method = controllerMethod.method();
        try {
            // 只支持无参方法
            method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void refresh() {
        // version 1: parse xml and register bean to mappingObjs... (没实现)
        // version 2: component scan
        initMapping();
    }

    record ControllerInstance(Class<?> clazz, Object obj) {}
    record ControllerMethod(Object obj, Method method) {}

    private void initController() {
        controllerNames = List.of(webApplicationContext.getBeanDefinitionNames());
        for (String controllerName : controllerNames) {
            try {
                final Class<?> clz = Class.forName(controllerName);
                controllerInstances.put(controllerName, new ControllerInstance(clz, webApplicationContext.getBean(controllerName)));
            } catch (ClassNotFoundException | BeansException e) {
                e.printStackTrace();
            }
        }
    }

    private void initMapping() {
        // controller 交给容器管理，只需要处理 mapping
        initController();
        for (String controllerName : this.controllerNames) {
            final ControllerInstance controllerInstance = controllerInstances.get(controllerName);
            Class<?> clazz = controllerInstance.clazz();
            Object obj = controllerInstance.obj();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
                if (isRequestMapping) {
                    String urlmapping = method.getAnnotation(RequestMapping.class).value();
                    mappingObjsMethods.put(urlmapping, new ControllerMethod(obj, method));
                }
            }
        }
    }

}
