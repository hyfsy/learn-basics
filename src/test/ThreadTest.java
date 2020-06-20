package test;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.*;

/**
 * 未开始的线程测试
 */
public class ThreadTest {
    public static void main(String[] args) {
        new Thread(System.out::println);
        final ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(System.nanoTime()+" " + System.currentTimeMillis());
//        executorService.submit(System.out::println);
//        ThreadPoolExecutor
//        ExecutorCompletionService
//        executorService.invokeAll()
//        BlockingQueue
//        ConcurrentHashMap
//        Collections.synchronizedMap()
    }
}
