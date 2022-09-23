package tdd.args;

import com.github.pyro233.tdd.args.Option;

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
            Parameter parameter = constructor.getParameters()[0];
            Option option = parameter.getAnnotation(Option.class);
            List<String> arguments = Arrays.asList(args);
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
            return (T) constructor.newInstance(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
