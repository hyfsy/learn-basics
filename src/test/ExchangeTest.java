package test;

/**
 * @author baB_hyf
 * @date 2021/07/08
 */
public class ExchangeTest {

    public static void main(String[] args) {
        int i = 555;
        int j = 999;
        System.out.println(i + "===" + j);

        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        System.out.println(i + "===" + j);
    }
}
