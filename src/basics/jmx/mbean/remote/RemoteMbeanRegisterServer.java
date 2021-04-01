package basics.jmx.mbean.remote;

import javax.management.*;
import javax.management.loading.MLet;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * MBean注册器，可注册远程MBean对象
 *
 * @author baB_hyf
 * @date 2021/04/01
 */
public class RemoteMbeanRegisterServer {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 注册MLet，该对象可用来注册其他MBean
        MLet mLet = new MLet();
        ObjectName objectNameMLet = new ObjectName("JMXMLet:type=MLet");
        mBeanServer.registerMBean(mLet, objectNameMLet);

        // 实际调用的注册方法
        // mLet.getMBeansFromURL("http://localhost:8701/mlet");

        // 启用一个rmi端口，方便客户端通过rmi连接
        Registry registry = LocateRegistry.createRegistry(1099);

        // 开启服务
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, mBeanServer);
        jmxConnectorServer.start();
    }

}
