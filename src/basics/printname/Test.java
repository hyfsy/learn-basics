package basics.printname;

import basics.printname.chinese.He;

/**
 * 测试打印汉字
 */
public class Test {
    public static void main(String[] args) {
        Animation animation = new Animation();
        animation.addPrint(He.class);
        animation.showPrint();
    }
}
