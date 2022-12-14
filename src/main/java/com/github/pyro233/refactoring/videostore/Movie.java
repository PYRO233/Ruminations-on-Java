package com.github.pyro233.refactoring.videostore;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/27 20:18
 */
public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private final String _title;
    // movie 可能会修改自己的分类，加入间接层
    private Price _price;

    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR -> _price = new RegularPrice();
            case NEW_RELEASE -> _price = new NewReleasePrice();
            case CHILDRENS -> _price = new ChildrensPrice();
            default -> throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return _title;
    }

    double getCharge(final int daysRented) {
        return _price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(final int daysRented) {
        return _price.getFrequentRenterPoints(daysRented);
    }

    public interface Price {

        double getCharge(int daysRented);

        default int getFrequentRenterPoints(int daysRented) {
            return 1;
        }
    }

    static class ChildrensPrice implements Price {

        @Override
        public double getCharge(final int daysRented) {
            double result = 1.5;
            if (daysRented > 3) result += (daysRented - 3) * 1.5;
            return result;
        }
    }

    static class RegularPrice implements Price {

        @Override
        public double getCharge(final int daysRented) {
            double result = 2;
            if (daysRented > 2) result += (daysRented - 2) * 1.5;
            return result;
        }
    }

    static class NewReleasePrice implements Price {

        @Override
        public double getCharge(final int daysRented) {
            return daysRented * 3;
        }

        @Override
        public int getFrequentRenterPoints(final int daysRented) {
            return (daysRented > 1) ? 2 : 1;
        }
    }

}
