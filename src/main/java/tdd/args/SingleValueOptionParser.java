package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;
import java.util.function.Function;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class SingleValueOptionParser<T> implements OptionParser {

    Function<String, T> valueParser;

    public SingleValueOptionParser(final Function<String, T> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public T parse(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return valueParser.apply(value);
    }

}
