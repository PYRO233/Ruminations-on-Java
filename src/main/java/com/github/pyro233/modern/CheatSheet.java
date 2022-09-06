package com.github.pyro233.modern;

import java.time.LocalDate;
import java.time.Month;

/**
 * 1. text blocks (JDK 15)
 * @Author: tao.zhou
 * @Date: 2022/8/25 23:21
 */
public class CheatSheet {

    @JDK16(name = "records", desc = "不可变的数据、透明的载体")
    record Circle(double radius) {
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    @JDK17(name = "sealed classes")
    public sealed abstract static class Shape permits Shape.Square, Shape.Rectangle {
        abstract double area();

        @JDK16(name = "Pattern Matching for instanceof")
        public static boolean isSquare(Shape shape) {
            // JEP 394: Pattern Matching for instanceof
            if (shape instanceof Rectangle rect) {
                return (rect.length == rect.width);
            }
            return (shape instanceof Square);
        }
        static final class Square extends Shape {
            public final double side;

            public Square(final double side) {
                this.side = side;
            }

            @Override
            public double area() {
                return side * side;
            }
        }
        public static final class Rectangle extends Shape {
            public final double length;
            public final double width;

            public Rectangle(double length, double width) {
                this.length = length;
                this.width = width;
            }

            @Override
            public double area() {
                return length * width;
            }
        }
    }

    @JDK14(name = "Switch Expressions")
    public static int getDaysInMonth(LocalDate date) {
        Month month = date.getMonth();
        boolean leapYear = date.isLeapYear();
        return switch (month) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> {
                if (leapYear) yield 29;
                else yield 28;
            }
            default -> throw new RuntimeException();
        };
    }
}
