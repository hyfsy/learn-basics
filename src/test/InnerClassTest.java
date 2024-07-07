package test;

/**
 * @author baB_hyf
 * @date 2022/02/25
 */
public class InnerClassTest {

    public static void main(String[] args) {
        InnerClassTest innerClassTest = new InnerClassTest();
        BBB bbb = innerClassTest.new BBB();
    }

    class BBB {

    }
}
