package tdd.args;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static tdd.args.BooleanOptionParserTest.option;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/24 11:53
 */
class SingleValueOptionParserTest {

    @Test // happy path (generic)
    public void should_parse_int_as_option_value() {
        Object original = new Object();
        Object parsed = new Object();
        Function<String, Object> parse = it -> parsed;
        assertSame(parsed, new SingleValueOptionParser<>(original, parse).parse(List.of("-p", "8080"), option("p")));
    }

    @Test // sad path
    public void should_not_accept_extra_argument_for_single_value_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValueOptionParser<>(0, Integer::parseInt).parse(List.of("-p", "8080", "8081"), option("p")));
        assertEquals("p", e.getOption());
    }

    @ParameterizedTest // sad path
    @ValueSource(strings = {"-p -l", "-p"})
    public void should_not_accept_insufficient_argument_for_single_value_option(String arguments) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValueOptionParser<>(0, Integer::parseInt).parse(List.of(arguments.split(" ")), option("p")));
        assertEquals("p", e.getOption());
    }

    @Test // default valu (generic)e
    public void should_set_default_value_to_0_for_int_option() {
        Function<String, Object> whatever = it -> null;
        Object defaultValue = new Object();
        assertSame(defaultValue, new SingleValueOptionParser<>(defaultValue, whatever).parse(List.of(), option("p")));
    }

}