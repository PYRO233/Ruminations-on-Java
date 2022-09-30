package com.github.pyro233.refactoring.catalog.composingmethods.replacemethodwithmethodobject;

public final class Gamma {
    private final Account account;
    private final int inputVal;
    private final int quantity;
    private final int yearToDate;

    public Gamma(Account account, int inputVal, int quantity, int yearToDate) {
        this.account = account;
        this.inputVal = inputVal;
        this.quantity = quantity;
        this.yearToDate = yearToDate;
    }

    private int importantValue1;
    private int importantValue2;
    private int importantValue3;

    int compute() {
        importantValue1 = (inputVal * quantity) + account.delta();
        importantValue2 = (inputVal * yearToDate) + 100;
        if ((yearToDate - importantValue1) > 100)
            importantValue2 -= 20;
        importantValue3 = importantValue2 * 7;
        return importantValue3 - 2 * importantValue1;
    }

}