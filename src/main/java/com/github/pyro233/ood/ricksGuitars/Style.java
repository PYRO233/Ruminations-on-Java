package com.github.pyro233.ood.ricksGuitars;

/**
 * @Author: tao.zhou
 * @Date: 2023/5/5 14:14
 */
public enum Style {

    A,
    F,
    ;

    public String toString() {
        return switch (this) {
            case A -> "A style";
            case F -> "F style";
        };
    }
}
