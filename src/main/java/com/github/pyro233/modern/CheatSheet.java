package com.github.pyro233.modern;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

/**
 * 1. text blocks (JDK 15)
 * 2. Pattern Matching for switch
 * 3. Vector API
 * 4. Foreign Function & Memory API
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

    @JDK17(name = "sealed classes", desc = "封闭类的子类可查可数")
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

    @JDK9(name = "Flow")
    public static class Transform<T, R> extends SubmissionPublisher<R> implements Processor<T, R> {

        private Subscription subscription;
        private Function<T, R> transform;

        public Transform(final Function<T, R> transform) {
            super();
            this.transform = transform;
        }

        @Override
        public void onSubscribe(final Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(final T item) {
            submit(transform.apply(item));
            subscription.request(1);
        }

        @Override
        public void onError(final Throwable throwable) {
            closeExceptionally(throwable);
        }

        @Override
        public void onComplete() {
            close();
        }
    }
}
