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

        // print banner
        printBanner();

        // calculate outstanding
        double outstanding = getOutstanding();

        // print details
        printDetails(outstanding);
    }

    private double getOutstanding() {
        Enumeration e = Collections.enumeration(_orders);
        double result = 0.0;
        while (e.hasMoreElements()) {
            Order each = (Order) e.nextElement();
            result += each.getAmount();
        }
        return result;
    }

    private void printDetails(final double outstanding) {
        System.out.println("name: " + _name);
        System.out.println("amount: " + outstanding);
    }

    private void printBanner() {
        System.out.println("***********************");
        System.out.println("**** Customer Owes ****");
        System.out.println("***********************");
    }

}
