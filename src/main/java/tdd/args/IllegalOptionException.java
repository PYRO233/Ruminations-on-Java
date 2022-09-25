package tdd.args;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/24 11:09
 */
public final class IllegalOptionException extends RuntimeException {

    private final String parameter;

    public IllegalOptionException(final String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
