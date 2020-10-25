package test;

import org.junit.Test;

/**
 * @author baB_hyf
 * @date 2020/10/04
 */
public class BinaryTests {

    @Test
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;

        // 进位用
        int c = 0;

        // 从后往前
        int i = a.length() - 1;
        int j = b.length() - 1;

        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {
            if (i >= 0) c += a.charAt(i--) - '0';
            if (j >= 0) c += b.charAt(j--) - '0';
            sb.append(c % 2);
            c >>= 1; // 11 > 1   10 > 1   01 > 0
        }

        String res = sb.reverse().toString();
        return c > 0 ? '1' + res : res;
    }

}
