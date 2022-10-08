package com.github.pyro233.refactoring.catalog.hidedelegate;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 15:36
 */
public class Person {
    private Department _department;
    public String Name;

    public Person(String name) {
        Name = name;
    }

    public Department getDepartment() {
        return _department;
    }

    public void setDepartment(Department arg) {
        _department = arg;
    }
}