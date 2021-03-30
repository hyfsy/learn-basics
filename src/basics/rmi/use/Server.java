package basics.rmi.use;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author baB_hyf
 * @date 2021/03/28
 */
public class Server {
    public static void main(String[] args) throws RemoteException {
        // 创建对象
        Hello hello = new HelloImpl();
        // 创建注册表
        Registry registry = LocateRegistry.createRegistry(1099);
        // 绑定对象到注册表，并给他取名为hello
        registry.rebind("hello", hello);
    }
}
