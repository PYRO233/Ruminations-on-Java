package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class BooleanOptionParser implements OptionParser {

    @Override
    public Object parse(final List<String> arguments, final Option option) {
        return arguments.contains("-" + option.value());
    }
}
