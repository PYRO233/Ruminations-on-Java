package com.github.pyro233.tdd.args;

import com.github.pyro233.tdd.args.exceptions.InsufficientArgumentsException;
import com.github.pyro233.tdd.args.exceptions.TooManyArgumentsException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class SingleValueOptionParser<T> implements OptionParser<T> {

    Function<String, T> valueParser;
    T defaultValue;

    public SingleValueOptionParser(final T defaultValue, final Function<String, T> valueParser) {
        this.valueParser = valueParser;
        this.defaultValue = defaultValue;
    }

    @Override
    public T parse(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) return defaultValue;
        List<String> values = valuesFrom(arguments, index);
        if (values.size() < 1)
            throw new InsufficientArgumentsException(option.value());
        if (values.size() > 1)
            throw new TooManyArgumentsException(option.value());
        return valueParser.apply(values.get(0));
    }

    private List<String> valuesFrom(final List<String> arguments, final int index) {
        return arguments.subList(index + 1, IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst().orElse(arguments.size()));
    }

}
