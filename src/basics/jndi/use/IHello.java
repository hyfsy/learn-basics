package basics.jndi.use;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author baB_hyf
 * @date 2021/03/29
 */
public interface IHello extends Remote {

    String sayHello(String name) throws RemoteException;
}
