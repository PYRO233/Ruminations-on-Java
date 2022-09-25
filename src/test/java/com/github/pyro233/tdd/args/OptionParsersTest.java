package com.github.pyro233.tdd.args;

import com.github.pyro233.tdd.args.exceptions.IllegalValueException;
import com.github.pyro233.tdd.args.exceptions.InsufficientArgumentsException;
import com.github.pyro233.tdd.args.exceptions.TooManyArgumentsException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/24 11:53
 */
class OptionParsersTest {
    @Nested
    class BooleanOptionParser {

        @Test // happy path
        public void should_set_boolean_option_to_true_if_flag_present() {
            assertTrue(OptionParsers.bool().parse(List.of("-l"), option("l")));
        }

        @Test // sad path
        public void should_not_accept_extra_argument_for_boolean_option() {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.bool().parse(List.of("-l", "t"), option("l")));
            assertEquals("l", e.getOption());
        }

        @Test // default value
        public void should_set_default_value_to_false_if_option_not_present() {
            assertFalse(OptionParsers.bool().parse(List.of(), option("l")));
        }
    }

    @Nested
    class UnaryOptionParser {
        // TODO Integer -3

        @Test // happy path (generic)
        public void should_parse_value_if_flag_present() {
            Object defaultValue = new Object();
            Object parsed = new Object();
            Function<String, Object> parse = it -> parsed;
            assertSame(parsed, OptionParsers.unary(defaultValue, parse).parse(List.of("-p", "8080"), option("p")));
        }

        @Test // sad path
        public void should_not_accept_extra_argument_for_single_value_option() {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.unary(0, Integer::parseInt).parse(List.of("-p", "8080", "8081"), option("p")));
            assertEquals("p", e.getOption());
        }

        @ParameterizedTest // sad path
        @ValueSource(strings = {"-p -l", "-p"})
        public void should_not_accept_insufficient_argument_for_single_value_option(String arguments) {
            InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                    () -> OptionParsers.unary(0, Integer::parseInt).parse(List.of(arguments.split(" ")), option("p")));
            assertEquals("p", e.getOption());
        }

        @Test // default value (generic)
        public void should_set_default_value_to_0_for_int_option() {
            Function<String, Object> whatever = it -> null;
            Object defaultValue = new Object();
            assertSame(defaultValue, OptionParsers.unary(defaultValue, whatever).parse(List.of(), option("p")));
        }

        @Test
        public void should_raise_exception_if_illegal_value_format() {
            Object defaultValue = new Object();
            Function<String, Object> parse = it -> {
                throw new RuntimeException();
            };
            IllegalValueException e = assertThrows(IllegalValueException.class,
                    () -> OptionParsers.unary(defaultValue, parse).parse(List.of("-p", "8080"), option("p")));
            assertEquals("p", e.getOption());
            assertEquals("8080", e.getValue());
        }
    }

    @Nested
    class ListOptionParser {
        @Test
        public void should_parse_list_value() {
            String[] values = OptionParsers.list(String[]::new, String::valueOf).parse(List.of("-g", "this", "is"), option("g"));
            assertArrayEquals(new String[]{"this", "is"}, values);
        }

        @Test
        public void should_use_empty_array_as_default_value() {
            String[] values = OptionParsers.list(String[]::new, String::valueOf).parse(List.of(), option("g"));
            assertEquals(0, values.length);
        }

        @Test
        public void should_throw_exception_if_value_parser_cant_parse_value() {
            Function<String, Object> parse = it -> {
                throw new RuntimeException();
            };
            IllegalValueException e = assertThrows(IllegalValueException.class,
                    () -> OptionParsers.list(String[]::new, parse).parse(List.of("-g", "this", "is"), option("g")));
            assertEquals("g", e.getOption());
            assertEquals("this", e.getValue());
        }
    }

    static Option option(String value) {
        return new Option() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Option.class;
            }

            @Override
            public String value() {
                return value;
            }
        };
    }
}