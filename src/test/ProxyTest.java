package test;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * 创建 jdk Proxy 的class
 *
 * @author baB_hyf
 * @date 2020/05/18
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        String fileName = "E:\\UserService.class";
        byte[] userService$ProxiesBytes = ProxyGenerator.generateProxyClass("userService$Proxy", new Class[]{UserService.class});
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(userService$ProxiesBytes);
        outputStream.flush();
        outputStream.close();
    }
}

interface UserService {
    void add();
}
