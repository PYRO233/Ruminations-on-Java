package com.github.pyro233.refactoring.videostore;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/27 20:18
 */
public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public String getTitle() {
        return _title;
    }

    double getCharge(final int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case REGULAR -> {
                result += 2;
                if (daysRented > 2)
                    result += (daysRented - 2) * 1.5;
            }
            case NEW_RELEASE -> result += daysRented * 3;
            case CHILDRENS -> {
                result += 1.5;
                if (daysRented > 3)
                    result += (daysRented - 3) * 1.5;
            }
        }
        return result;
    }

    int getFrequentRenterPoints(final int daysRented) {
        // add bonus for a two day new release rental
        if ((getPriceCode() == NEW_RELEASE) && daysRented > 1)
            return 2;
        else return 1;
    }
}
