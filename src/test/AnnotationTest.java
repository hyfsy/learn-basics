package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author baB_hyf
 * @date 2021/03/23
 */
public class AnnotationTest {

    @Test
    public void test1() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        m(stringList);
    }

    @SafeVarargs // Not actually safe!
    // @SuppressWarnings("vararg")
    static void m(List<String>... stringLists) {
        Object[] array = stringLists;
        List<Integer> tmpList = Collections.singletonList(42);
        array[0] = tmpList; // Semantically invalid, but compiles without warnings
        String s = stringLists[0].get(0); // Oh no, ClassCastException at runtime!
        System.out.println(s);
    }
}
