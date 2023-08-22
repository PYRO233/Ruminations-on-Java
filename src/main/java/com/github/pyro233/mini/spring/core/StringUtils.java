package com.github.pyro233.mini.spring.core;

/**
 * @Author: tao.zhou
 * @Date: 2023/8/21 21:49
 */
public class StringUtils {
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }
}
