package tdd.args;

import com.github.pyro233.tdd.args.Option;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/21 16:13
 */
public class ArgsTest {

    @Test
    public void should_set_boolean_option_to_true_if_flag_present() {
        ArgsTest.BooleanOption option = Args.parse(ArgsTest.BooleanOption.class, "-l");
        assertTrue(option.logging());
    }

    @Test
    public void should_set_boolean_option_to_false_if_flag_not_present() {
        ArgsTest.BooleanOption option = Args.parse(ArgsTest.BooleanOption.class);
        assertFalse(option.logging());
    }

    static record BooleanOption(@Option("l") boolean logging) {
    }

    @Test
    public void should_parse_int_as_option_value() {
        ArgsTest.IntOption option = Args.parse(ArgsTest.IntOption.class, "-p", "8080");
        assertEquals(8080, option.port());
    }

    static record IntOption(@Option("p") int port) {
    }

    @Test
    public void should_get_string_as_option_value() {
        ArgsTest.StringOption option = Args.parse(ArgsTest.StringOption.class, "-d", "/usr/logs");
        assertEquals("/usr/logs", option.directory());
    }

    static record StringOption(@Option("d") String directory) {
    }

    @Test
    public void should_parse_multi_options() {
        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    static record MultiOptions(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {
    }

    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "3", "5");
        assertEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertEquals(new int[]{1, 2, 3, 5}, options.decimals());
    }

    static record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {
    }
}
