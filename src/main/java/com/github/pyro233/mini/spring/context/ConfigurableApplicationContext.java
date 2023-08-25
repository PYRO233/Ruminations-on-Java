package com.github.pyro233.mini.spring.context;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/24 23:52
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void setParent(ApplicationContext parent);

}
