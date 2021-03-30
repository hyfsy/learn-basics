package basics.jndi.use;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author baB_hyf
 * @date 2021/03/29
 */
public class IHelloImpl extends UnicastRemoteObject implements IHello {

    protected IHelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello " + name;
    }
}
