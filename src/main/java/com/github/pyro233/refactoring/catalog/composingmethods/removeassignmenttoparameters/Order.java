package com.github.pyro233.refactoring.catalog.composingmethods.removeassignmenttoparameters;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/28 23:55
 */
public class Order {

    public int discount(int inputVal, int quantity, int yearToDate) {
        if (inputVal > 50) inputVal -= 2;
        if (quantity > 100) inputVal -= 1;
        if (yearToDate > 10000) inputVal -= 4;
        return inputVal;
    }

}
