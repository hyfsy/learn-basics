package basics.processor;

/**
 * 先编译annotation、processor，再编译main
 *
 * javac -encoding utf-8 basics/processor/AllGetter*
 * javac -processor basics.processor.AllGetterProcessor basics/processor/TestProcessor.java
 *
 * @author baB_hyf
 * @date 2020/07/04
 */
@AllGetter
public class TestProcessor {
    public static void main(String[] args) {
        System.out.println("start finish");
    }
}
