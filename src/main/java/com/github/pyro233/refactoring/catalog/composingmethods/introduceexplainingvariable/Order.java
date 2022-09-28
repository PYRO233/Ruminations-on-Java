package com.github.pyro233.refactoring.catalog.composingmethods.introduceexplainingvariable;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/28 23:37
 */
public class Order {

    private int _quantity;
    private int _itemPrice;

    public Order(int quantity, int itemPrice) {
        _quantity = quantity;
        _itemPrice = itemPrice;
    }

    public double price() {
        // price is base price - quantity discount + shipping
        return basePrice() - quantityDiscount() + shipping();
    }

    private double shipping() {
        return Math.min(basePrice() * 0.1, 100.0);
    }

    private double quantityDiscount() {
        return Math.max(0, _quantity - 500) * _itemPrice * 0.05;
    }

    private int basePrice() {
        return _quantity * _itemPrice;
    }

}
