package test.serialize;

import java.io.Serializable;

/**
 * @author baB_hyf
 * @date 2021/03/22
 */
public class P extends Per implements Serializable {

    private String name;
    private int    age;

    // public P() {
    // }

    public P(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + "P{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
