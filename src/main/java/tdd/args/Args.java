package tdd.args;

import com.github.pyro233.tdd.args.Option;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

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

    @Nullable
    private static Object parseOption(final List<String> arguments, final Parameter parameter) {
        Option option = parameter.getAnnotation(Option.class);
        String target = "-" + option.value();
        Object value = null;
        if (parameter.getType() == boolean.class) {
            value = arguments.contains(target);
        }
        if (parameter.getType() == int.class) {
            int index = arguments.indexOf(target);
            value = Integer.parseInt(arguments.get(index + 1));
        }
        if (parameter.getType() == String.class) {
            int index = arguments.indexOf(target);
            value = arguments.get(index + 1);
        }
        return value;
    }
}
