package com.github.pyro233.tdd.args;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/25 23:10
 */
public class UnsupportedOptionTypeException extends RuntimeException {

    private final String option;

    private final Class<?> type;

    public UnsupportedOptionTypeException(final String option, final Class<?> type) {
        this.option = option;
        this.type = type;
    }

    public String getOption() {
        return option;
    }

    public Class<?> getType() {
        return type;
    }
}
