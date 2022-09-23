package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:16
 */
class StringOptionParser implements OptionParser {

    @Override
    public Object parse(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        return arguments.get(index + 1);
    }
}
