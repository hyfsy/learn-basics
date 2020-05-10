package basics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);
        for (int i = 0; i < 3; i++) {
            //使用Set remove(Object);
            set.remove(i);
            //使用List remove(index);
            list.remove(i);
        }
        System.out.println(set + " " + list);
    }
}
