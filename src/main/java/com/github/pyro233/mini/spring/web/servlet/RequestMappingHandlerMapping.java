package com.github.pyro233.mini.spring.web.servlet;

import com.github.pyro233.mini.spring.beans.BeansException;
import com.github.pyro233.mini.spring.web.RequestMapping;
import com.github.pyro233.mini.spring.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/25 8:44
 */
public class RequestMappingHandlerMapping implements HandlerMapping {

    private WebApplicationContext wac;

    private final MappingRegistry mappingRegistry = new MappingRegistry();

    public RequestMappingHandlerMapping(WebApplicationContext wac) {
        this.wac = wac;
        initMapping();
    }

    @Override
    public HandlerMethod getHandler(HttpServletRequest httpServletRequest) {
        final String servletPath = httpServletRequest.getServletPath();
        final ControllerMethod controllerMethod = mappingRegistry.getMappingObjsMethods()
                                                                 .get(servletPath);
        return Optional.ofNullable(controllerMethod).map(it -> new HandlerMethod(it.obj(), it.method())).orElse(null);
    }

    private void initMapping() {
        final String[] controllerNames = wac.getBeanDefinitionNames();
        for (String controllerName : controllerNames) {
            Class<?> clazz;
            try {
                clazz = Class.forName(controllerName);
                Object obj = wac.getBean(controllerName);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
                    if (isRequestMapping) {
                        String urlmapping = method.getAnnotation(RequestMapping.class).value();
                        mappingRegistry.getMappingObjsMethods().put(urlmapping, new ControllerMethod(obj, method));
                    }
                }
            } catch (ClassNotFoundException | BeansException e) {
                e.printStackTrace();
            }
        }
    }

    static class MappingRegistry {

        // mapping: uri -> class, method
        private final Map<String, ControllerMethod> mappingObjsMethods = new HashMap<>();

        public Map<String, ControllerMethod> getMappingObjsMethods() {
            return mappingObjsMethods;
        }
    }

    record ControllerMethod(Object obj, Method method) {}
}
