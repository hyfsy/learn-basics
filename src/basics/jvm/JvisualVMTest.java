package basics.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试gc回收及jvisualvm的使用
 *
 * @author baB_hyf
 * @date 2020/04/25
 */
public class JvisualVMTest {

    //声明缓存对象
    private static final Map map = new HashMap();

    public static void main(String args[]){
        try {
            Thread.sleep(10000);//给打开visualvm时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //循环添加对象到缓存
        for(int i=0; i<1000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("first");
        //为dump出堆提供时间
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<1000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("second");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<6000000;i++){
            TestMemory t = new TestMemory();
            map.put("key"+i,t);
        }
        System.out.println("third");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        for(int i=0; i<4000000;i++){
//            TestMemory t = new TestMemory();
//            map.put("key"+i,t);
//        }
//        System.out.println("forth");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("qqqq");
    }
}

class TestMemory{
    private String id;
    private String name;

}