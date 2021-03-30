package basics.rmi.use;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author baB_hyf
 * @date 2021/03/28
 */
public interface Hello extends Remote, Serializable {

    String welcome(String name) throws RemoteException;
}

