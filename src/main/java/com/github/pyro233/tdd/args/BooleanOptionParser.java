package com.github.pyro233.tdd.args;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:15
 */
class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(final List<String> arguments, final Option option) {
        return SingleValueOptionParser.values(arguments, option, 0).isPresent();
    }
}
