package test.serialize;

import org.junit.Test;
import test.serialize.Person;

import java.io.*;

/**
 * @author baB_hyf
 * @date 2021/03/22
 */
public class SerializableTest {

    @Test
    public void test1() throws IOException, ClassNotFoundException {
        String path = "E:\\person.txt";
        Person person = new Person("张三", 19);

        // ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        // oos.writeObject(person);
        // oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Person o = (Person)ois.readObject();
        System.out.println(o);
        ois.close();
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        String path = "E:\\person2.txt";
        P person = new P("张三", 19);
        person.setId(1);
        System.out.println(person);

        // ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        // oos.writeObject(person);
        // oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        P o = (P)ois.readObject();
        System.out.println(o);
        ois.close();
    }

}
