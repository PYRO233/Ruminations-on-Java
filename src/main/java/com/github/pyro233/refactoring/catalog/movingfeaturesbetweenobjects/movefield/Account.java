package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.movefield;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 14:28
 */
public class Account {
    private AccountType _type;
    private double _interestRate;

    public Account(AccountType type, double interestRate) {
        _type = type;
        _interestRate = interestRate;
    }

    public double interestForAmountDays(double amount, int days) {
        return _interestRate * amount * days / 365;
    }
}
