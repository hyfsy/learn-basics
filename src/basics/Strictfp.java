package basics;

/**
 * strictfp可以保证浮点数运算的精确性
 *
 * 一旦使用了strictfp来声明一个类、接口或者方法，那么在所声明的范围内，
 * Java编译器以及运行环境会完全依照IEEE二进制浮点数算术标准来执行，
 * 在这个关键字声明的范围内所有浮点数的计算都是精确的
 */
public strictfp class Strictfp {
    int a = 1;

    public static void testStrictfp() {
        float f = 0.12365f;
        double d = 0.03496421d;
        double sum = f + d;
        System.out.println(sum);
    }

    public static void main(String[] args) {
        testStrictfp();
    }
}
