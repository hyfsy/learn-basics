package test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author baB_hyf
 * @date 2020/10/28
 */
public class RegexTest {
    static Pattern pattern  = Pattern.compile("\\{\\{([^}]*)\\}\\}");
    static Pattern pattern2 = Pattern.compile("\\{{2}[^}]+}{2}");

    public static void main(String[] args) {
        String regex = "";
        Matcher matcher = pattern.matcher("asdf{{asdf}}as{{}}df{{asdf1}}asd{{dddd2}}");

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            System.out.println(matcher.group());
            String content = matcher.group(1);
            matcher.appendReplacement(sb, content);
            System.out.println();
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());

        System.out.println("--------------------------");

        Matcher matcher2 = pattern2.matcher("asdf{{asdf}}asdf{{asdf1}}asd{{dddd2}}");

        while (matcher2.find()) {
            System.out.println(matcher2.group());

        }


        // boolean b2 = matcher.find();
        // System.out.println(b2);
        // String group2 = matcher.group();
        // System.out.println(group2);
    }

    @Test
    public void test2() {
        Matcher matcher = pattern.matcher("a{{}}asdfaaa");

        System.out.println(matcher.lookingAt()); // 开头匹配
        System.out.println(matcher.find());
    }

    @Test
    public void test3() {
        Pattern pattern = Pattern.compile("A*(B*(C*(D*)))");
        Matcher matcher = pattern.matcher("AAABBBCCCDDD");
        while (matcher.find()) {
            System.out.println(matcher.group(0)); // AAABBBCCCDDD
            System.out.println(matcher.group(1)); // BBBCCCDDD
            System.out.println(matcher.group(2)); // CCCDDD
            System.out.println(matcher.group(3)); // DDD
        }
    }

    @Test
    public void test4() {
        Matcher matcher = pattern.matcher("asdf{{asdf1}}asdf{{asdf2}}asdf");

        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            String holder = matcher.group(0);// {{asdf1}}
            String value = matcher.group(1);// asdf1
            matcher.appendReplacement(sb, value.toUpperCase()); // 往sb中替换添加指定值
        }
        matcher.appendTail(sb); // 添加最后一个匹配结果后的剩余的数据

        System.out.println(sb.toString());
    }
}
