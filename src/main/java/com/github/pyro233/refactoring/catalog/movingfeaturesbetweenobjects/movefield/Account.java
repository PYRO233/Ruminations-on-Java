package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.movefield;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 14:28
 */
public class Account {
    private AccountType _type;

    public Account(AccountType type) {
        _type = type;
    }

    public double interestForAmountDays(double amount, int days) {
        return _type.getInterestRate() * amount * days / 365;
    }
}
