package tdd.args;

import com.github.pyro233.tdd.args.Option;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/24 11:06
 */
class BooleanOptionParserTest {

    @Test // happy path
    public void should_set_boolean_option_to_true_if_flag_present() {
        assertTrue(new BooleanOptionParser().parse(List.of("-l"), option("l")));
    }

    @Test // sad path
    public void should_not_accept_extra_argument_for_boolean_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new BooleanOptionParser().parse(List.of("-l", "t"), option("l")));
        assertEquals("l", e.getOption());
    }

    @Test // default value
    public void should_set_default_value_to_false_if_option_not_present() {
        assertFalse(new BooleanOptionParser().parse(List.of(), option("l")));
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