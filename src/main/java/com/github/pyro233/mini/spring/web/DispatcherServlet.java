package com.github.pyro233.mini.spring.web;

import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.beans.annotation.Autowired;
import com.github.pyro233.mini.spring.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 未经过测试（不想引入 Tomcat）
 * @Author: tao.zhou
 * @Date: 2023/8/22 19:50
 */
public class DispatcherServlet extends HttpServlet {

    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";

    private WebApplicationContext webApplicationContext;

    private String contextConfigLocation;

    private List<String> packageNames = new ArrayList<>();
    private Map<String, ControllerInstance> controllerInstances = new HashMap<>();
    private List<String> controllerNames = new ArrayList<>();

    // mapping: uri -> class, method
    private Map<String, ControllerMethod> mappingObjsMethods = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // (没实现，不想引入 web.xml)
        webApplicationContext = (WebApplicationContext) servletConfig.getServletContext()
                                    .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        // ServletConfig 和 ServletContext initParameter 不一样
        contextConfigLocation = servletConfig.getInitParameter(CONFIG_LOCATION_PARAM);
        try {
            final URL xmlPath = servletConfig.getServletContext().getResource(contextConfigLocation);
            packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
            refresh();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        super.init(servletConfig);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String requestURI = httpServletRequest.getRequestURI();
        httpServletRequest.getServletPath();
        final ControllerMethod controllerMethod = mappingObjsMethods.get(requestURI);
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
        initController();
        initMapping();
    }

    record ControllerInstance(Class<?> clazz, Object obj) {}
    record ControllerMethod(Object obj, Method method) {}

    private void initController() {
        controllerNames = scanPackages(packageNames);
        for (String controllerName : controllerNames) {
            try {
                final Class<?> clz = Class.forName(controllerName);
                final Object obj = clz.newInstance();

                populateBean(obj);

                controllerInstances.put(controllerName, new ControllerInstance(clz, obj));
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | BeansException e) {
                e.printStackTrace();
            }
        }
    }

    // copy from SimpleAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization
    private Object populateBean(Object bean) throws BeansException {
        final Class<?> clazz = bean.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            final boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                final String autowiredBeanName = field.getName();
                final Object autowiredBean = webApplicationContext.getBean(autowiredBeanName);
                field.setAccessible(true);
                try {
                    field.set(bean, autowiredBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    private void initMapping() {
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

    private List<String> scanPackages(List<String> packageNames) {
        return packageNames.stream().map(this::scanPackage)
                           .filter(Objects::nonNull)
                           .flatMap(Collection::stream).toList();
    }

    private Collection<String> scanPackage(String packageName) {
        List<String> tempControllerNames = new ArrayList<>();
        final URL url = getClass().getClassLoader()
                                       .getResource("/" + packageName.replaceAll("\\.", "/"));
        // Files.walkFileTree
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanPackage(packageName + "." + file.getName());
            } else {
                String controllerName = packageName + "." + file.getName()
                                                                .replace(".class", "");
                tempControllerNames.add(controllerName);
            }
        }
        return tempControllerNames;
    }


}
