package test;

import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.UnaryOperator;

public class HelloTest {

    public static void main(String[] args) {
        System.out.println(1<<24|1<<16|1);
        System.out.println();
        String.class.cast("asdf");

        ArrayList list = new ArrayList<>();
        new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        list.toArray();
        ThreadLocalRandom current = ThreadLocalRandom.current();


        String s = "";
        s = s.replace("\n", "").replace("\t", "");
        System.out.println(s);
    }
}
