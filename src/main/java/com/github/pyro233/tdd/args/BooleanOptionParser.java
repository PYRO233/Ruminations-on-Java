package com.github.pyro233.tdd.args;

import com.github.pyro233.tdd.args.exceptions.TooManyArgumentsException;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(final List<String> arguments, final Option option) {
        int index = arguments.indexOf("-" + option.value());
        if (index == -1) return false;

        List<String> values = SingleValueOptionParser.valuesFrom(arguments, index);

        if (values.size() > 0) throw new TooManyArgumentsException(option.value());
        return true;
    }
}
