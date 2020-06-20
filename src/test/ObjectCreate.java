package test;

/**
 * 对象创建时的一些操作的执行过程
 */
public class ObjectCreate {
    public static void main(String[] args) {
        new Son();
//        Son.test();
    }
}

class Father {

    static {
        System.out.println("father静态代码块");
    }

    {
        System.out.println("father代码块");
    }

    public Father() {
        System.out.println("father构造");
    }

}

class Son extends Father {

    static {
        System.out.println("son静态代码块");
    }

    {
        System.out.println("son代码块");
    }

    public Son() {
        super();//必须放在第一行  和 this() 冲突
        System.out.println("son构造");
    }

    public static void test(){
        System.out.println("test");
    }

}