package basics.jndi.use;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

/**
 * @author baB_hyf
 * @date 2021/03/29
 */
public class CallService {

    public static void main(String[] args) throws Exception{

        //配置JNDI工厂和JNDI的url和端口。如果没有配置这些信息，会出现NoInitialContextException异常
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");

        // 创建初始化环境
        Context ctx = new InitialContext(env);

        // 创建一个rmi映射表
        Registry registry = LocateRegistry.createRegistry(1099);
        // 创建一个对象
        IHello hello = new IHelloImpl();
        // 将对象绑定到rmi注册表
        registry.bind("hello", hello);

        Reference refObj = new Reference("refClassName", "insClassName", "http://axin.com:6666/");
        ReferenceWrapper refObjWrapper = new ReferenceWrapper(refObj);
        registry.bind("refObj", refObjWrapper);

        //  jndi的方式获取远程对象
        IHello rhello = (IHello) ctx.lookup("rmi://localhost:1099/hello");
        // 调用远程对象的方法
        System.out.println(rhello.sayHello("hello"));
    }
}
