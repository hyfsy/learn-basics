package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * eclipse idea compile diff
 *
 * @author baB_hyf
 * @date 2021/10/14
 */
public class GenericCompile {

    public static void main(String[] args) {
        Object m = get();

        Map<String, String> mmm = new HashMap<>();

        Map<String, String> bbb = (Map<String, String>) m;
        for (Map.Entry<String, String> entry : bbb.entrySet()) {
            mmm.put(entry.getKey(), entry.getValue());
        }

        System.out.println(mmm);
    }

    public static <T> T get() {
        try {
            Method m = GenericCompile.class.getDeclaredMethod("g");
            try {
                Object invoke = m.invoke(null);
                return (T) invoke;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Map<String, List<String>> g() {
        Map<String, List<String>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("a", new ArrayList<>());
        return stringListHashMap;
    }
}
