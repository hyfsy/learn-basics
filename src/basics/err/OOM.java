package basics.err;

import java.util.ArrayList;
import java.util.List;

/**
 * 两种Error
 */
public class OOM {
    public static void main(String[] args) {
        outofmemory();
        stackoverflow();
    }

    /**
     * 内存溢出
     */
    public static void outofmemory() {
        List<Object> list = new ArrayList<>();
        try {
            while (true) {
                Object o = new Object();
                list.add(o);//可能创建速度大于回收速度
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    /**
     * 堆栈溢出
     */
    public static void stackoverflow() {
        try {
            stackoverflow();
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
    }
}
