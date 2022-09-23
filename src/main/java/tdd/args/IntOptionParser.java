package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class IntOptionParser implements OptionParser {

    @Override
    public Object parse(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return parseValue(value);
    }

    protected Object parseValue(final String value) {
        return Integer.parseInt(value);
    }
}
