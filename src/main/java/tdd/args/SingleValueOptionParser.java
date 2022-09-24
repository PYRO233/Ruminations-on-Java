package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;
import java.util.function.Function;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class SingleValueOptionParser implements OptionParser {

    Function<String, Object> valueParser;

    public SingleValueOptionParser(final Function<String, Object> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public Object parse(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return valueParser.apply(value);
    }

}
