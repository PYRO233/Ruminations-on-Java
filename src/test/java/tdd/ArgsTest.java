package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/21 16:13
 */
public class ArgsTest {

    @Test
    public void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        Assertions.assertTrue(options.logging());
        Assertions.assertEquals(8080, options.port());
        Assertions.assertEquals("/usr/logs", options.directory());
    }

    @Test
    public void should_example_1() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "3", "5");
        Assertions.assertEquals(new String[]{"this", "is", "a", "list"}, options.group());
        Assertions.assertEquals(new int[]{1, 2, 3, 5}, options.decimals());
    }

    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {
    }

    static record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {
    }
}
