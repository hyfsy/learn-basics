package test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 不常用操作及对象及方法
 */
public class HelloTest {

    public static void main(String[] args) {
        // ???
        System.out.println(1 << 24 | 1 << 16 | 1);

        // 线程安全的随机数
        ThreadLocalRandom current = ThreadLocalRandom.current();

        // Class的cast方法
        String.class.cast("asdf");
    }
}
