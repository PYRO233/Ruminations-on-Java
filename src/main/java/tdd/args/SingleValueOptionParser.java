package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;
import java.util.function.Function;

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
        if (index + 1 == arguments.size() || arguments.get(index + 1).startsWith("-"))
            throw new InsufficientArgumentsException(option.value());
        if (index + 2 < arguments.size() && !arguments.get(index + 1).startsWith("-"))
            throw new TooManyArgumentsException(option.value());
        return valueParser.apply(arguments.get(index + 1));
    }

}
