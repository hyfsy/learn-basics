package basics.agent.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 类字节码文件转换器（通过javassist实现字节码的转换）
 *
 * @author baB_hyf
 * @date 2020/06/20
 */
public class MyClassFileTransformer1 implements ClassFileTransformer {

    /**
     * 自定义修改的类名
     */
    private String replaceClassName;

    /**
     * 自定义修改的方法名
     */
    private String replaceMethodName;

    /**
     * 类在vm中的名称
     */
    private String replaceVMClassName;

    public MyClassFileTransformer1(String replaceClassName, String replaceMethodName) {
        this.replaceClassName = replaceClassName;
        this.replaceMethodName = replaceMethodName;
        // 【/】 分割
        this.replaceVMClassName = replaceClassName.replaceAll("\\.", "\\/");
    }

    /**
     * 将加载的字节码文件数组转换为其他的字节码数组，该方法会在字节码文件加载到虚拟机之前执行
     *
     * @param loader              当前加载字节码文件的类加载器
     * @param className           当前加载字节码文件的类名，如 java/util/List
     * @param classBeingRedefined 当前加载字节码文件的类对象
     * @param protectionDomain    当前类的保护域，公共方法没多少
     * @param classfileBuffer     原本的类字节码
     * @return 修改后的类的字节码数组
     * @throws IllegalClassFormatException ...
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        // 不是我们的目标类，返回原本的字节码数组
        if(!className.equals(this.replaceVMClassName)) {
            return classfileBuffer;
        }

        try {
            // 通过类获取方法
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get(this.replaceClassName);
            CtMethod ctMethod = ctClass.getDeclaredMethod(this.replaceMethodName);

            // 增强方法
            ctMethod.insertBefore("{ System.out.println(\"start\"); }");
            ctMethod.insertAfter("{ System.out.println(\"end\"); }");

            // 返回新的字节码
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classfileBuffer;
    }
}
