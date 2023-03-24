package com.github.pyro233.modern;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 1. text blocks (JDK 15)
 * 2. Pattern Matching for switch
 * 3. Vector API
 * 4. Foreign Function & Memory API
 *
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

    /**
     * Refactoring Java 8 code with Java 17 new features - JEP Café #9
     * see https://www.youtube.com/watch?v=wW7uzc61tZ8
     * 1. distinct letters : distinctLetters
     * 2. get the top 3 most used letters : mostFrequentLetters
     * 3. partitioning the words : todo
     */
    public static void topLetters() {
        @JDK15(name = "text blocks")
        String haiku = """
                Breaking Through                  Pavement                  Wakin' with Bacon        Homeward Found
                ----------------                  --------                  -----------------        --------------
                The wall disappears               Beautiful pavement!       Wakin' with Bacon        House is where I am
                As soon as you break through the  Imperfect path before me  On a Saturday morning    Home is where I want to be
                Intimidation                      Thank you for the ride    Life’s little pleasures  Both may be the same

                Winter Slip and Slide              Simple Nothings                With Deepest Regrets
                ---------------------              ---------------                --------------------
                Run up the ladder                  A simple flower                With deepest regrets
                Swoosh down the slide in the snow  Petals shine vibrant and pure  That which you have yet to write
                Winter slip and slide              Stares into the void           At death, won't be wrote

                Caffeinated Coding Rituals  Finding Solace               Curious Cat                Eleven
                --------------------------  --------------               -----------                ------
                I arrange my desk,          Floating marshmallows        I see something move       This is how many
                refactor some ugly code,    Cocoa brewed hot underneath  What it is, I am not sure  Haiku I write before I
                and drink my coffee.        Comfort in a cup             Should I pounce or not?    Write a new tech blog.
                """;
        List<String> distinctLetters = haiku.chars().filter(Character::isAlphabetic).map(Character::toLowerCase)
                .mapToObj(Character::toString)  // because there is no charStream
                .distinct().toList();

        final Map<String, Long> charToStringCountMap = haiku.chars().filter(Character::isAlphabetic).map(Character::toLowerCase)
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final List<Map.Entry<String, Long>> mostFrequentLetters1 = charToStringCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .toList();

        // region invert map
        final Map<Character, Long> charCountMap = haiku.chars().filter(Character::isAlphabetic).map(Character::toLowerCase)
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final Map<Long, List<Character>> revertedCharMap = charCountMap.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

        final List<Map.Entry<Long, List<Character>>> mostFrequentLetters2 = revertedCharMap.entrySet().stream()
                .sorted(Map.Entry.<Long, List<Character>>comparingByKey().reversed())
                .limit(3)
                .toList();
        // endregion

        // region record version
        @JDK16(name = "records")
        record Letter(int codePoint) {
            Letter(int codePoint) {
                this.codePoint = Character.toLowerCase(codePoint);
            }
        }
        record LetterCount(long count) implements Comparable<LetterCount> {

            @Override
            public int compareTo(@NotNull final LetterCount other) {
                return Long.compare(this.count, other.count);
            }
        }
        final Map<Letter, LetterCount> recordMap = haiku.chars().filter(Character::isAlphabetic)
                .mapToObj(Letter::new)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.collectingAndThen(Collectors.counting(), LetterCount::new)));
        final Map<LetterCount, List<Letter>> revertedRecordMapUsingEntry = recordMap.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

        record LetterByCount(Letter letter, LetterCount count) {
            LetterByCount(Map.Entry<Letter, LetterCount> entry) {
                this(entry.getKey(), entry.getValue());
            }
        }
        final Map<LetterCount, List<Letter>> revertedRecordMapUsingRecord = recordMap.entrySet().stream().map(LetterByCount::new)
                .collect(Collectors.groupingBy(LetterByCount::count,
                        Collectors.mapping(LetterByCount::letter, Collectors.toList())));

        record LettersByCount(LetterCount count, List<Letter> letters) {
            LettersByCount(Map.Entry<LetterCount, List<Letter>> entry) {
                this(entry.getKey(), entry.getValue());
            }

            public static Comparator<? super LettersByCount> comparingByCount() {
                return Comparator.comparing(LettersByCount::count);
            }
        }
        final List<LettersByCount> mostFrequentLetters3 = revertedRecordMapUsingRecord.entrySet().stream().map(LettersByCount::new)
                .sorted(LettersByCount.comparingByCount().reversed())
                .limit(3).toList();
        // endregion
    }
}
