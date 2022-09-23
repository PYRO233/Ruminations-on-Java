package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.util.List;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:14
 */
interface OptionParser {
    Object parse(List<String> arguments, Option option);
}
