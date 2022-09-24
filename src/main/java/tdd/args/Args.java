package tdd.args;

import com.github.pyro233.tdd.args.Option;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: tao.zhou
 * @Date: 2022/9/22 22:46
 */
public class Args {
    public static <T> T parse(final Class<T> optionsClass, final String... args) {
        try {
            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            List<String> arguments = Arrays.asList(args);
            Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(arguments, it)).toArray();
            return (T) constructor.newInstance(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object parseOption(final List<String> arguments, final Parameter parameter) {
        return getOptionParser(parameter.getType()).parse(arguments, parameter.getAnnotation(Option.class));
    }

    private static Map<Class<?>, OptionParser> PARSERS = Map.of(
            boolean.class, new BooleanOptionParser(),
            int.class, new SingleValueOptionParser(Integer::parseInt),
            String.class, new SingleValueOptionParser(String::valueOf));


    private static OptionParser getOptionParser(final Class<?> type) {
        return PARSERS.get(type);
    }


}
