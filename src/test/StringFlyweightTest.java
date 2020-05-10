package test;

/**
 * String的享元对象池
 */
public class StringFlyweightTest {

    public static void main(String[] args) {
        String s1 = "asdf";
        String s2 = "as";
        String s3 = "df";
        String s4;
        s4 = s2 + s3;//new StringBuilder(s2).append(s3).toString();
        System.out.println(s1 == s4);//false
        //从string对象池中取出对象
        s4 = ("as" + "df").intern();
        System.out.println(s1 == s4);//true

        //JDK6的执行结果为：false
        //JDK7和JDK8的执行结果为：true
        String s5 = new String("1") + new String("1");
        System.out.println(s5 == s5.intern());


        String str6 = "b";
        String str7 = "a" + str6;
        String str67 = "ab";
        System.out.println("str7 = str67 : " + (str7 == str67));
        // ↑str6为变量，在运行期才会被解析。
        final String str8 = "b";
        String str9 = "a" + str8;
        String str89 = "ab";
        // System.out.println("str9 = str89 : " + (str9 == str89));
        // ↑str8为常量变量，编译期会被优化


    }

}
