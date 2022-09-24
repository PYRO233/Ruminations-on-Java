package tdd.args;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/24 11:09
 */
public final class TooManyArgumentsException extends RuntimeException {

    private final String option;

    public TooManyArgumentsException(final String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
