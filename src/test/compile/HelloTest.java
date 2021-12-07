package test.compile;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HelloTest {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println(System.getProperty("aaa"));

        System.out.println(1 << 24 | 1 << 16 | 1);

        ThreadLocalRandom current = ThreadLocalRandom.current();

        String.class.cast("asdf");
    }
}
