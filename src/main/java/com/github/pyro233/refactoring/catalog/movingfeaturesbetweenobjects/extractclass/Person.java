package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.extractclass;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 14:58
 */
public class Person {
    private String _name;
    private String _officeAreaCode;
    private String _officeNumber;

    public Person(String name, String officeAreaCode, String officeNumber) {
        _name = name;
        _officeAreaCode = officeAreaCode;
        _officeNumber = officeNumber;
    }

    public String getName() {
        return _name;
    }

    public String getTelephoneNumber() {
        return ("(" + _officeAreaCode + ") " + _officeNumber);
    }

    public String getOfficeAreaCode() {
        return _officeAreaCode;
    }

    void setOfficeAreaCode(String arg) {
        _officeAreaCode = arg;
    }

    public String getOfficeNumber(String arg) {
        return _officeNumber;
    }

    void setOfficeNumber(String arg) {
        _officeNumber = arg;
    }
}