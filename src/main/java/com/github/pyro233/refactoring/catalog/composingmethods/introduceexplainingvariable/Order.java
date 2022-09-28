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
        final int basePrice = _quantity * _itemPrice;
        final double quantityDiscount = Math.max(0, _quantity - 500) * _itemPrice * 0.05;
        final double shipping = Math.min(basePrice * 0.1, 100.0);
        return basePrice - quantityDiscount + shipping;
    }

}
