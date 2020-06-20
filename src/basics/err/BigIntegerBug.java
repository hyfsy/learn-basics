package basics.err;

import java.math.BigInteger;

/**
 * 由于BigInteger的pow执行时JVM没有插入safepoint,导致大量运算时线程一直无法进入safepoint，
 * 而GC线程也在等待这个Java线程进入safepoint才能开始GC，结果JVM就Freezen了
 * 修复了
 */
public class BigIntegerBug {
    public static void main(String[] args) {
        // Spawn a background thread to compute an enormous number.
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
                System.out.println("=========================");
                BigInteger.valueOf(5).pow(100000000);
                System.out.println("=========================");
            }
        }.start();

// Loop, allocating memory and periodically logging progress, so illustrate GC pause times.
        byte[] b;
        for (int outer = 0; ; outer++) {
            long startMs = System.currentTimeMillis();
            for (int inner = 0; inner < 100000; inner++) {
                b = new byte[1000];
            }

            System.out.println("Iteration " + outer + " took " + (System.currentTimeMillis() - startMs) + " ms");
        }
    }
}
