package basics.rmi.use;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author baB_hyf
 * @date 2021/03/28
 */
public class HelloImpl extends UnicastRemoteObject implements Hello {

    public HelloImpl() throws RemoteException {
    }

    @Override
    public String welcome(String name) throws RemoteException {
        System.out.println("welcome");
        return "Hello, " + name;
    }
}