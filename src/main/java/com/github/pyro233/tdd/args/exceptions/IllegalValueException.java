package com.github.pyro233.tdd.args.exceptions;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/25 22:36
 */
public class IllegalValueException extends RuntimeException {

    private final String option;

    private final String value;

    public IllegalValueException(final String option, final String value) {
        this.option = option;
        this.value = value;
    }

    public String getOption() {
        return option;
    }

    public String getValue() {
        return value;
    }
}
