package com.github.pyro233.refactoring.catalog.composingmethods.replacetempwithquery;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/28 21:30
 */
public class Order {

    private int _itemPrice;
    private int _quantity;

    public Order(int itemPrice, int quantity) {
        _itemPrice = itemPrice;
        _quantity = quantity;
    }

    public double getPrice() {
        return basePrice() * discountFactor();
    }

    private double discountFactor() {
        double discountFactor;
        if (basePrice() > 1000) discountFactor = 0.95;
        else discountFactor = 0.98;
        return discountFactor;
    }

    private int basePrice() {
        return _quantity * _itemPrice;
    }

}
