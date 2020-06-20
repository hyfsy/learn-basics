package basics.jvm;

/**
 * 测试线程阻塞及jstack的使用
 *
 * @author baB_hyf
 * @date 2020/04/21
 */
public class JstackTest {

    public static void main(String[] args) {

        Thread thread = new Thread(new Worker());

        thread.start();

    }

    static class Worker implements Runnable {

        @Override

        public void run() {

            while (true) {

                System.out.println("Thread Name:" + Thread.currentThread().getName());

            }

        }

    }

}