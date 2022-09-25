package com.github.pyro233.tdd.args;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:14
 */
interface OptionParser<T> {
    T parse(List<String> arguments, Option option);
}
