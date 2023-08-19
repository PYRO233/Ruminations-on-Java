package com.github.pyro233.mini.spring.core;

import java.util.Iterator;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/19 10:14
 */
// interface Resource extends Iterator with generic T
public interface Resource<T> extends Iterator<T> {

}


// class ClassPathXmlResource implements Resource
