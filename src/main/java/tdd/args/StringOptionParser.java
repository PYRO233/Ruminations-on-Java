package tdd.args;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/23 17:16
 */
class StringOptionParser extends IntOptionParser {

    public StringOptionParser() {
        super(String::valueOf);
    }
}
