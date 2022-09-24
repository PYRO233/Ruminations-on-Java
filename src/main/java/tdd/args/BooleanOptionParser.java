package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index + 1 < arguments.size() && !arguments.get(index + 1).startsWith("-"))
            throw new TooManyArgumentsException(option.value());
        return index != -1;
    }
}
