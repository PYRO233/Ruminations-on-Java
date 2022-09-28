package com.github.pyro233.refactoring.catalog.composingmethods.extractmethod;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/28 20:40
 */
public class Order {
    private double _amount;

    public Order(double amount) {
        _amount = amount;
    }

    public double getAmount() {
        return _amount;
    }
}
