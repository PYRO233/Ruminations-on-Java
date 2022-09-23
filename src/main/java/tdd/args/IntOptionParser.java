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
        return Integer.parseInt(arguments.get(index + 1));
    }
}
