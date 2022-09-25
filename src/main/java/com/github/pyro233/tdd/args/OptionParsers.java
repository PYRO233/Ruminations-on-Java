package com.github.pyro233.tdd.args;

import com.github.pyro233.tdd.args.exceptions.IllegalValueException;
import com.github.pyro233.tdd.args.exceptions.InsufficientArgumentsException;
import com.github.pyro233.tdd.args.exceptions.TooManyArgumentsException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class OptionParsers {

    public static OptionParser<Boolean> bool() {
        return (arguments, option) -> values(arguments, option, 0).isPresent();
    }

    public static <T> OptionParser<T> unary(final T defaultValue, final Function<String, T> valueParser) {
        return (arguments, option) -> values(arguments, option, 1).map(it -> parse(option, valueParser, it.get(0))).orElse(defaultValue);
    }

    private static <T> T parse(final Option option, final Function<String, T> valueParser, final String value) {
        try {
            return valueParser.apply(value);
        } catch (Exception e) {
            throw new IllegalValueException(option.value(), value);
        }
    }

    public static <T> OptionParser<T[]> list(final IntFunction<T[]> generator, final Function<String, T> valueParser) {
        return ((arguments, option) -> {
            Optional<List<String>> strings = values(arguments, option);
            return strings
                    .map(list -> list.stream().map(it -> parse(option, valueParser, it)).toArray(generator)).orElse(generator.apply(0));
        });
    }

    private static Optional<List<String>> values(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        return Optional.ofNullable(index == -1 ? null : valuesFrom(arguments, index));
    }

    protected static Optional<List<String>> values(final List<String> arguments, final Option option, final int expectedSize) {
        return values(arguments, option).map(it -> checkSize(option, expectedSize, it));
    }

    private static List<String> checkSize(final Option option, final int expectedSize, final List<String> values) {
        if (values.size() < expectedSize)
            throw new InsufficientArgumentsException(option.value());
        if (values.size() > expectedSize)
            throw new TooManyArgumentsException(option.value());
        return values;
    }

    protected static List<String> valuesFrom(final List<String> arguments, final int index) {
        return arguments.subList(index + 1, IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst().orElse(arguments.size()));
    }

}
