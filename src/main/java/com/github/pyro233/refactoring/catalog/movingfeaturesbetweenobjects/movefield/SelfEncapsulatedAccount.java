package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.movefield;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 14:29
 */
public class SelfEncapsulatedAccount {
    private AccountType _type;

    public SelfEncapsulatedAccount(AccountType type) {
        _type = type;
    }

    public double interestForAmountDays(double amount, int days) {
        return getInterestRate() * amount * days / 365;
    }

    private void setInterestRate(double arg) {
        _type.setInterestRate(arg);
    }

    private double getInterestRate() {
        return _type.getInterestRate();
    }
}