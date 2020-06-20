package basics.agent.javassist;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.LinkedList;
import java.util.List;

/**
 * java agent javassist方式实现
 * 需要将agent打成一个jar包，并在启动时加载或运行时加载
 *
 * <p>
 * 参考：https://mp.weixin.qq.com/s/dTyfMghXj0Gt_ledjgB0-A
 *
 * @author baB_hyf
 * @date 2020/06/20
 */
public class AgentDemo1 {

    /**
     * 我们自己指定的增强类和方法
     */
    public static String className = "basics.agent.javassist.HelloController";
    private static String methodName = "hello";

    /**
     * 方法签名固定，instrumentation参数可省略
     * <p>
     * 启动时，类加载前定义类的 Transformer，在类加载的时候更新类的字节码
     *
     * @param agentArgs 系统运行时的 -javaagent 参数
     * @param instrumentation 插装，便于测量Java编程语言代码
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        instrumentation.addTransformer(new MyClassFileTransformer1(className, methodName));
    }

    /**
     * 方法签名固定，instrumentation参数可省略
     * <p>
     * 用于在运行时进行字节码的修改
     *
     * @param agentArgs 系统运行时的 -javaagent 参数
     * @param instrumentation 插装，便于测量Java编程语言代码
     */
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        List<Class<?>> needTransformClass = new LinkedList<>();
        Class<?>[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class<?> allLoadedClass : allLoadedClasses) {
            // 此处只会有一个需要增强的类
            if (allLoadedClass.getName().equals(className)) {
                needTransformClass.add(allLoadedClass);
            }
        }

        try {

            // 执行类字节码转换
            instrumentation.addTransformer(new MyClassFileTransformer1(className, methodName));
            instrumentation.retransformClasses(needTransformClass.toArray(new Class[0]));

        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }
}
