package test;

// jdk 9
//import java.lang.ref.Cleaner;

import java.io.*;
import java.util.Objects;

public class CleanTest {

    private String s;

    public static void main(String[] args) {


//        Integer.compare()
//        String.CASE_INSENSITIVE_ORDER;
        System.out.println(CleanTest.class instanceof Object);
        try {
            PrintStream printStream = new PrintStream("asdf");
            printStream.print("asdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void switchIsNull(){
        String s = null;
        switch (s) {
            case "1":
                System.out.println("1");
                break;
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
        System.out.println("结束switch");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleanTest cleanTest = (CleanTest) o;
        return Objects.equals(s, cleanTest.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }
}
