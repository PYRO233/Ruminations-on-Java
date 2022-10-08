package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.extractclass;

public class TelephoneNumber {
    private String _officeAreaCode;
    private String _officeNumber;

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