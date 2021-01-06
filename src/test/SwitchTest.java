package test;

import org.junit.Test;

/**
 * @author baB_hyf
 * @date 2020/10/25
 */
public class SwitchTest {

    /**
     * switch支持的类型：
     * char, byte, short, int, Character, Byte, Short, Integer, String, or an enum
     */
    @Test
    public void testSwitch() {

        // byte可作用在switch上

        byte a = 'a';

        char c = '啊'; // char存储一个汉字

        switch (a) {
            case '1':
                break;
            case 'a':
                break;
        }



        long l = 11L;

        // switch (l) {
        //     case 1:
        //        break;
        // }
    }
}
