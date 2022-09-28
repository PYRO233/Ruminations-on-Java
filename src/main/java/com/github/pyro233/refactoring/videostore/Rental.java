package com.github.pyro233.refactoring.videostore;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/27 20:20
 */
public class Rental {

    private final Movie _movie;
    private final int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    double getCharge() {
        return _movie.getCharge(getDaysRented());
    }

    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(getDaysRented());
    }

}
