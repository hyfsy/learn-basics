package test;

import java.lang.reflect.Field;

/**
 * 典型享元模式测试
 *
 * @author baB_hyf
 * @date 2020/09/19
 */
public class IntegerTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<?> cache = Integer.class.getDeclaredClasses()[0]; // 1
        Field myCache = cache.getDeclaredField("cache"); // 2
        myCache.setAccessible(true); // 3

        Integer[] newCache = (Integer[]) myCache.get(cache); // 4 缓存总共256，-128~127
        newCache[132] = newCache[133]; // 5 修改缓存4的位置的值为5

        int a = 2;
        int b = a + a; // 原始值4在下方变为包装类时调用valueOf方法寻找缓存索引为4的值
        System.out.printf("%d + %d = %d", a, a, b);
    }
}
