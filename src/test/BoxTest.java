package test;

/**
 * 自动装箱耗时测试
 */
public class BoxTest {
    public static void main(String[] args) {
        Yes();
        No();
    }

    public static void No() {
        final long l1 = System.currentTimeMillis();
        Long sum = 0L;
        for (long l = 0; l < Integer.MAX_VALUE; l++) {
            sum += l;
        }
        System.out.println("总数：" + sum);
        System.out.println("总耗时：" + (System.currentTimeMillis() - l1));
    }

    public static void Yes() {
        final long l1 = System.currentTimeMillis();
        long sum = 0L;
        for (long l = 0; l < Integer.MAX_VALUE; l++) {
            sum += l;
        }
        System.out.println("总数：" + sum);
        System.out.println("总耗时：" + (System.currentTimeMillis() - l1));
    }
}
