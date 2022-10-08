package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.hidedelegate;

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

    public void setDepartment(Department arg) {
        _department = arg;
    }

    public Person getManager() {
        return _department.getManager();
    }
}