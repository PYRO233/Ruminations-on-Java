package com.github.pyro233.refactoring.catalog.organizingdata.changevaluetoreference;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/10 10:43
 */
public final class Customer {

    public static final Map<String, Customer> _instances = new HashMap<>();

    static {
        new Customer ("Lemon Car Hire").store();
        new Customer ("Associated Coffee Machines").store();
        new Customer ("Bilston Gasworks").store();
    }

    private void store() {
        _instances.put(this.getName(), this);
    }

    private final String name;

    private Customer(String name) {
        this.name = name;
    }

    // Replace Constructor with Factory Method
    public static Customer getNamed(String name) {
        return _instances.get(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Customer) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
