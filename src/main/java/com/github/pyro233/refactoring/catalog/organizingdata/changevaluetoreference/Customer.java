package com.github.pyro233.refactoring.catalog.organizingdata.changevaluetoreference;

import java.util.Objects;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/10 10:43
 */
public final class Customer {

    private final String name;

    public Customer(String name) {
        this.name = name;
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
