package basics.agent.javassist.test;

import basics.agent.javassist.HelloController;
import com.sun.tools.attach.VirtualMachine;

/**
 * 调用时，可以使用 -javaagent:[jar-path] 导入jar包
 *      -javaagent:E:\study\idea2\learn-basics\src\basics\agent\javassist\lib\agent-test.jar
 * 或者使用 com.sun.tools.attach.VirtualMachine（java的lib文件夹下）
 *
 * @author baB_hyf
 * @date 2020/06/20
 */
public class AgentTest {

    public static void main(String[] args) {
        // loadJavaAgent();
        new HelloController().hello();
    }

    /**
     * 第二种加载agent文件方式
     */
    public void loadJavaAgent() {
        String agentFilePath = "E:\\study\\idea2\\learn-basics\\src\\basics\\agent\\javassist\\lib\\agent-test.jar";
        String jvmPid = "目标进行的pid";
        try {
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent(agentFilePath);
            jvm.detach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
