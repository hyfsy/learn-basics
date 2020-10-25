package test;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 典型享元模式测试
 *
 * @author baB_hyf
 * @date 2020/09/19
 */
public class IntegerTest {

    @Test
    public void testInt() {
        int a = 1;
        int b = a + a; // 原始值2在下方变为包装类时调用valueOf方法寻找缓存索引为2的值
        System.out.printf("%d + %d = %d", a, a, b);
    }

    @Before
    public void before() throws NoSuchFieldException, IllegalAccessException {
        Class<?> cache = Integer.class.getDeclaredClasses()[0]; // 1
        Field myCache = cache.getDeclaredField("cache"); // 2
        myCache.setAccessible(true); // 3

        Integer[] newCache = (Integer[]) myCache.get(cache); // 4 缓存总共256，-128~127
        newCache[130] = newCache[131]; // 5 修改缓存2的位置的值为3
    }
}
