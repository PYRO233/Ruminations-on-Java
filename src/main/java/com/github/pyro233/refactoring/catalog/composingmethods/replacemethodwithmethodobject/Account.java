package com.github.pyro233.refactoring.catalog.composingmethods.replacemethodwithmethodobject;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/29 0:06
 */
public class Account {

    public int gamma(int inputVal, int quantity, int yearToDate) {
        return new Gamma(this, inputVal, quantity, yearToDate).compute();
    }

    public int delta() {
        return 100;
    }

}
