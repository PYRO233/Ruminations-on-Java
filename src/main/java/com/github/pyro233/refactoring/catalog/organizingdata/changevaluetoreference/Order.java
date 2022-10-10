package com.github.pyro233.refactoring.catalog.organizingdata.changevaluetoreference;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/10 10:44
 */
public class Order {

    private Customer _customer;

    public Order(String customerName) {
        _customer = Customer.getNamed(customerName);
    }

    public void setCustomer(String customerName) {
        _customer = Customer.getNamed(customerName);
    }

    public String getCustomerName() {
        return _customer.getName();
    }

    private static int numberOfOrdersFor(List<Order> orders, String customer) {
        int result = 0;
        for (final Order order : orders) {
            if (order.getCustomerName().equals(customer)) result++;
        }
        return result;
    }
}
