package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.hidedelegate;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 15:36
 */
public class Department {
    private String _chargeCode;
    private Person _manager;

    public Department(Person manager) {
        _manager = manager;
    }

    public Person getManager() {
        return _manager;
    }
}