package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.movemethod;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 11:22
 */
public class Account {

    private final AccountType _type;
    private final int _daysOverdrawn;

    public Account(AccountType type, int daysOverdrawn) {
        _type = type;
        _daysOverdrawn = daysOverdrawn;
    }

    public double overdraftCharge() {
        if (_type.isPremium()) {
            double result = 10;
            if (_daysOverdrawn > 7) result += (_daysOverdrawn - 7) * 0.85;
            return result;
        } else return _daysOverdrawn * 1.75;
    }

    public double bankCharge() {
        double result = 4.5;
        if (_daysOverdrawn > 0) result += overdraftCharge();
        return result;
    }

}
