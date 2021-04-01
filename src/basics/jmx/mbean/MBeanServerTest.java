package basics.jmx.mbean;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 实现MBean必须接口存在该后缀，实现类名称对应该接口名前缀
 * 通过ObjectName为key将实体注册进服务中后，可在jvisualvm 中查看其属性值及操作方法
 * MBean 和 MXBean 的区别在于 MXBean 还可以操作实体中非基本类型的属性（通过将属性转换为 CompositeDataSupport）
 * <p>
 * 附加cmd启动命令：jvisualvm --console suppress
 *
 * @author baB_hyf
 * @date 2020/06/16
 */
public class MBeanServerTest {

    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName mbeanName = new ObjectName("basics.jmx.mbean:type=Test,name=Person");
        Person mbean = new Person(888, "张三", 18);
        mBeanServer.registerMBean(mbean, mbeanName);

        // System.out.println("Wait ...");
        // Thread.sleep(Long.MAX_VALUE);

        // 创建本地服务端
        String ip = "localhost";
        int port = 1099;
        // 连接需要先启动rmi服务
        Registry registry = LocateRegistry.createRegistry(port);
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + ip + ":" + port + "/jmxrmi");
        JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, mBeanServer);
        jmxConnectorServer.start();
    }
}
