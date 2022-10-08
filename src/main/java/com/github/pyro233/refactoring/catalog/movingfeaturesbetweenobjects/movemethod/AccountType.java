package com.github.pyro233.refactoring.catalog.movingfeaturesbetweenobjects.movemethod;

/**
 * @Author: tao.zhou
 * @Date: 2022/10/8 11:24
 */
public class AccountType {

    private final boolean _isPremium;

    public AccountType(boolean isPremium) {
        _isPremium = isPremium;
    }

    public boolean isPremium() {
        return _isPremium;
    }

}
