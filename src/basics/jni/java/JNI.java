
package basics.jni.java;

// https://zhuanlan.zhihu.com/p/563584554
// javac JNI.java -h jni
public class JNI {

    // -Djava.library.path=E:\\study\\code\\idea2\\learn-basics\\src\\basics\\jni\\jni_java\\jni
    static {
        // System.setProperty("java.library.path", "E:\\study\\code\\idea2\\learn-basics\\src\\basics\\jni\\jni_java\\jni");
        System.loadLibrary("libhello_jni2");
    }

    public static void main(String[] args) {
        new JNI().displayHelloWorld();
    }

    public native void displayHelloWorld();
}
