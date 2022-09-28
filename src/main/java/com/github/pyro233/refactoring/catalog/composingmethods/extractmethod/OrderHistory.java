package com.github.pyro233.refactoring.catalog.composingmethods.extractmethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/28 20:39
 */
public class OrderHistory {
    private ArrayList<Order> _orders;
    private String _name;

    public OrderHistory(ArrayList<Order> orders, String name) {
        _orders = orders;
        _name = name;
    }

    public void printOwing() {
        Enumeration e = Collections.enumeration(_orders);
        double outstanding = 0.0;

        // print banner
        System.out.println("***********************");
        System.out.println("**** Customer Owes ****");
        System.out.println("***********************");

        // calculate outstanding
        while (e.hasMoreElements()) {
            Order each = (Order) e.nextElement();
            outstanding += each.getAmount();
        }

        // print details
        System.out.println("name: " + _name);
        System.out.println("amount: " + outstanding);
    }

}
