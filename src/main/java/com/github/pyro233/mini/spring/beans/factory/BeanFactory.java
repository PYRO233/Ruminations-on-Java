package com.github.pyro233.mini.spring.beans.factory;

import com.github.pyro233.mini.spring.beans.BeansException;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 9:57
 */
public interface BeanFactory {

    // method getBean
    Object getBean(String name) throws BeansException;

}
