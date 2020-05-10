package basics.spi;

import java.util.ServiceLoader;

/**
 * @author baB_hyf
 * @date 2020/02/26
 */
public class SpiTest {

    public static void main(String[] args){
        ServiceLoader<PersonI> persons = ServiceLoader.load(PersonI.class);
        for (PersonI person : persons) {
            person.say();
        }
    }
}
