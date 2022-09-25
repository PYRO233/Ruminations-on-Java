package com.github.pyro233.tdd.args.exceptions;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/24 11:09
 */
public final class InsufficientArgumentsException extends RuntimeException {

    private final String option;

    public InsufficientArgumentsException(final String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
