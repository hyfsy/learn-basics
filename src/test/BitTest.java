package test;

import org.junit.Test;

/**
 * @author baB_hyf
 * @date 2020/10/10
 */
public class BitTest {


    @Test
    public void testXXX() {

        byte[] bytes = new byte[]{'a', -'w', -3, -9, -0, 'd', '&', '-'};
        for (byte b : bytes) {
            System.out.println(b & 0xff);
            System.out.println(b);
        }


        // off = -0b010101010;
        // len = -9;
        System.out.println(0xff);
        System.out.println(0b11111111);
        System.out.println(0b10000001);


        // 计算机内的存储都是利用二进制的补码进行存储的

        // 正数：原码反码补码都是本身
        // 负数：
        // 原码  -3 10000011
        // 反码 252 11111100
        // 补码 253 11111101

        System.out.println(-3 & 0xff);
        System.out.println(0b11111101); // byte 8位
        System.out.println(0b11111111111111111111111111111101); // int 32位

        // 当byte要转化为int的时候，高的24位必然会补1
        // -3 & 0xff   ->   0b11111111111111111111111111111101 & 0b11111111
        System.out.println(0b00000000000000000000000011111101); // 保证二进制数据一致


        /*
               目的：
               保持二进制补码的一致性，因为计算机保存机制是：正数存储的二进制原码,负数存储的是二进制的补码
               如果是十六进制的0f，默认只显示f，此时要补上0
               当系统检测到byte可能会转化成int或者说byte与int类型进行运算的时候，就会将byte的内存空间高位补1
               （也就是按符号位补位）扩充到32位，再参与运算
         */


        int off = 11;
        int len = 10;
        byte[] b = new byte[11];

        System.out.println(off | len | (off + len));

        // 通过"|"运算确保off,len大于或等于0,b.length大于或等于off+len
        System.out.println(off | len | (off + len) | (b.length - (off + len)));


    }
}
