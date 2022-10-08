package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.extractclass;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 14:58
 */
public class Person {
    private final TelephoneNumber _officeTelephone = new TelephoneNumber();
    private String _name;

    public Person(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public String getTelephoneNumber() {
        return _officeTelephone.getTelephoneNumber();
    }

    public TelephoneNumber getOfficeTelephone() {
        return _officeTelephone;
    }
}