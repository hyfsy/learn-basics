package basics.rmi.attack;

import basics.rmi.use.Hello;
import basics.rmi.use.HelloImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 被攻击者，注册任意类，类反序列化时被攻击
 *
 * @author baB_hyf
 * @date 2021/03/28
 */
public class Server {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);

        // 保证服务器处于启动状态
        Hello hello = new HelloImpl();
        registry.bind("hello", hello);
    }
}
