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
    // movie 可能会修改自己的分类，加入间接层
    private Price _price;

    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return _price.getPriceCode();
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

    int getFrequentRenterPoints(final int daysRented) {
        // add bonus for a two day new release rental
        if ((getPriceCode() == NEW_RELEASE) && daysRented > 1)
            return 2;
        else return 1;
    }

    public interface Price {
        int getPriceCode();

        default double getCharge(int daysRented) {
            final int priceCode = getPriceCode();
            return switch (priceCode) {
                case REGULAR -> {
                    double result = 2;
                    if (daysRented > 2)
                        result += (daysRented - 2) * 1.5;
                    yield result;
                }
                case NEW_RELEASE -> daysRented * 3;
                case CHILDRENS -> {
                    double result = 1.5;
                    if (daysRented > 3)
                        result += (daysRented - 3) * 1.5;
                    yield result;
                }
                default -> throw new IllegalArgumentException("Incorrect Price Code");
            };
        }
    }

    class ChildrensPrice implements Price {
        @Override
        public int getPriceCode() {
            return CHILDRENS;
        }
    }

    class RegularPrice implements Price {
        @Override
        public int getPriceCode() {
            return REGULAR;
        }
    }

    class NewReleasePrice implements Price {
        @Override
        public int getPriceCode() {
            return NEW_RELEASE;
        }
    }

}
