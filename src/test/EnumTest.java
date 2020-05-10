package test;


import java.io.OptionalDataException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.LinkOption;
import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EnumTest {
    Food food;

    enum Food{
        NODDLE("面条"),RISA("米饭");

        String name;

        Food(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) {
         Comparator.naturalOrder();
        Map<String, String> map = new HashMap<>();
        String s = "";
        String s1 = map.computeIfAbsent(s, (unused) -> "");
        final Optional<String> s11 = Optional.of(s1);
        Optional.ofNullable("");
        final OptionalInt optionalInt = OptionalInt.of(3);

        Method method = null;
        try {
            method.invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Food[] foods= new Food[Food.values().length];
        Stream<Food> foodStream = Arrays.stream(foods);
        // foodStream.collect(Collectors.groupingBy(f->f.food,()-> new EnumMap<>(Food.class),Collectors.toSet());
    }
}
