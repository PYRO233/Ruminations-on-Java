package com.github.pyro233.mini.spring.web.context;

import com.github.pyro233.mini.spring.beans.DefaultListableBeanFactory;
import com.github.pyro233.mini.spring.beans.config.BeanDefinition;
import com.github.pyro233.mini.spring.beans.factory.HierarchicalBeanFactory;
import com.github.pyro233.mini.spring.beans.factory.ListableBeanFactory;
import com.github.pyro233.mini.spring.context.ApplicationContext;
import com.github.pyro233.mini.spring.web.servlet.DispatcherServlet;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 将 {@link DispatcherServlet} 中创建 Controller 的代码抽取出来，使用容器管理。 因此不需要再去创建 Controller，注册到容器中即可。
 * 引入两级 ApplicationContext, AnnotationConfigWebApplicationContext 支持 scan controller, {@link XmlWebApplicationContext} 支持 xml 配置 service。
 * 1. {@link ApplicationContext#getParent()} 先启动的是 parentApplicationContext。
 * 2. {@link HierarchicalBeanFactory#getParentBeanFactory()} getBean 取不到时去 parentBeanFactory 中找。
 * 3. 接口隔离，set 方法需要引入 ConfigurableXXX 接口。
 *
 * @Author: tao.zhou
 * @Date: 2023/8/24 23:46
 */
public class AnnotationConfigWebApplicationContext extends AbstractWebApplicationContext {

    private DefaultListableBeanFactory defaultBeanFactory;

    public AnnotationConfigWebApplicationContext(List<String> packageNames, WebApplicationContext parentApplicationContext) {
        setParent(parentApplicationContext);
        defaultBeanFactory = new DefaultListableBeanFactory(parentApplicationContext.getBeanFactory());
        // 边 scan 边 loadBeanDefinition 或者先 scan 再 loadBeanDefinition
        final List<String> controllerNames = scanPackages(packageNames);
        loadBeanDefinitions(controllerNames);
    }

    @Override
    public ListableBeanFactory getBeanFactory() {
        return defaultBeanFactory;
    }

    private List<String> scanPackages(List<String> packageNames) {
        return packageNames.stream().map(this::scanPackage)
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

    /**
     * 将 {@link DispatcherServlet} 中创建 Controller 的代码抽取出来，使用容器管理。
     * 因此不需要再去创建 Controller，注册到容器中即可。
     */
    private void loadBeanDefinitions(List<String> controllerNames) {
        for (String beanID : controllerNames) {
            final String beanClassName = beanID;
            final BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            defaultBeanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }

}
