
package com.batchsight.demo.test;

// https://zhuanlan.zhihu.com/p/563584554
// javac JNI.java -h jni
public class JNI {

    // -Djava.library.path=C:\\Users\\user\\Desktop\\test\\test\\demo-parent\\demo\\src\\test\\java\\com\\batchsight\\demo\\test\\jni
    static {
        // System.setProperty("java.library.path", "C:\\Users\\user\\Desktop\\test\\test\\demo-parent\\demo\\src\\test\\java\\com\\batchsight\\demo\\test\\jni");
        System.loadLibrary("libhello_jni2");
    }

    public static void main(String[] args) {
        new JNI().displayHelloWorld();
    }

    public native void displayHelloWorld();
}
