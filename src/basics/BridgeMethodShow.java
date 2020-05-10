package basics;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 测试桥接方法
 *
 * {@link BridgeMethodResolver}
 *
 * @author baB_hyf
 * @date 2020/04/11
 */
public class BridgeMethodShow {
    public static void main(String[] args) throws Exception {

        // 使用接口定义，不指定具体实现类
        MainInterface mainInterface = new MainInterfaceImpl();
        Method bridgeMethod = mainInterface.getClass().getMethod("generateBridgeMethod", Object.class);
        System.out.println("Is Bridge Method: " + bridgeMethod.isBridge());


//        // 此处编译通过，但运行报错
//        // 因为执行的是桥接方法，内部调用类型擦除后的实际方法进行强转
        mainInterface.generateBridgeMethod(new Date());
    }
}





interface MainInterface<T> {
    void generateBridgeMethod(T coreField);
}

class MainInterfaceImpl implements MainInterface<String> {

    @Override
    public void generateBridgeMethod(String coreField) {
        System.out.println(coreField);
        System.out.println("it's a common method");
    }

//    public void generateBridgeMethod(Object bridge) {
//        System.out.println(bridge);
//    }
}
