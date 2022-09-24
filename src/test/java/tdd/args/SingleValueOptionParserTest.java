package tdd.args;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tdd.args.BooleanOptionParserTest.option;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/24 11:53
 */
class SingleValueOptionParserTest {

    @Test
    public void should_not_accept_extra_argument_for_single_value_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValueOptionParser<>(0, Integer::parseInt).parse(List.of("-p", "8080", "8081"), option("p")));
        assertEquals("p", e.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-p -l", "-p"})
    public void should_not_accept_insufficient_argument_for_single_value_option(String arguments) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValueOptionParser<>(0, Integer::parseInt).parse(List.of(arguments.split(" ")), option("p")));
        assertEquals("p", e.getOption());
    }

    @Test
    public void should_set_default_value_to_0_for_int_option() {
        new SingleValueOptionParser<>(0, Integer::parseInt).parse(List.of(), option("p"));
    }

}