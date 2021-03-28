package test;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author baB_hyf
 * @date 2021/03/19
 */
public class StandardTest {

    @Test
    public void testSplit() {
        String s = "1,2,3,,"; // hope 4
        String[] split = s.split(",");
        System.out.println(split.length);
        System.out.println(Arrays.asList(split));

    }

    @Test
    public void testYear() {
        int i = LocalDate.now().lengthOfYear();
        System.out.println(i);
    }

    @Test
    public void testLock() {
        Lock lock = new ReentrantLock();
        try {
            if (lock != null) {
                throw new RuntimeException("error");
            }
            lock.lock();
            System.out.println(1);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 多写的线程安全问题
     */
    @Test
    public void testVolatile() {
        // volatile int a = 1;
    }

    @Test
    public void testIntSwitch() {
        Integer i = 200;
        Character c = 'd';
        System.out.println(i.hashCode());
        System.out.println(c.hashCode());
    }
}
