package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.cnblogs.com/woshixiaobai/p/4262973.html
 *
 * @author baB_hyf
 * @date 2021/09/16
 */
public class NetTest {

    public static void main(String[] args) throws IOException {
        // simpleUse();
        // securityProperty();
        // hostname();
        // convert();
        // isXXX();
        // v4v6();

        // interfaceUse();
        // reachable();

        // socket();
        // timeout();
        // socketInfo();
        // socketOptions();
    }

    public static void simpleUse() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("baidu.com");
        System.out.println(address);

        InetAddress[] addresses = InetAddress.getAllByName("baidu.com");
        for (InetAddress inetAddress : addresses) {
            System.out.println(inetAddress);
        }

        System.out.println(InetAddress.getByName("192.168.1.1"));

        System.out.println(InetAddress.getLocalHost());

        byte[] bytes = new byte[]{(byte) 141, (byte) 146, 8, 66};
        System.out.println(InetAddress.getByAddress(bytes));
        System.out.println(InetAddress.getByAddress("Oracle网站", bytes));
    }

    public static void securityProperty() {
        // /jdk1.8.0_171/jre/lib/security/java.security
        Security.setProperty("networkaddress.cache.ttl", "10"); // dns缓存，默认-1永久，s
        Security.setProperty("networkaddress.cache.negative.ttl", "10"); // 缓存访问无效是否以后也直接报错UnknownHost，默认是，s
    }

    public static void hostname() throws UnknownHostException {
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println(localhost.getHostName()); // 本机名

        InetAddress address = InetAddress.getByName("141.146.8.66");
        System.out.println(address.getHostName());  // 只有通过ip创建的才会在getHostName调用时访问DNS服务器

        System.out.println(address.getCanonicalHostName()); // 实际的主机域名，getHostName获取的是别名，因为方便，有时候也只能通过别名来访问
    }

    public static void convert() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("www.csdn.net");
        // byte -128 ~ 127，ip表示需要转为int或更大的
        // ip   0 ~ 255
        byte[] bytes = address.getAddress();
        int[] ip = new int[4];
        int i = 0;
        for (byte b : bytes) {
            int segment = b;
            segment = segment < 0 ? segment + 256 : segment; // 转换
            ip[i++] = segment;
        }
        List<String> ips = Arrays.stream(ip).boxed().map(String::valueOf).collect(Collectors.toList());
        System.out.println(String.join(".", ips));
    }

    public static void isXXX() throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("0.0.0.0");

        // 通配符地址
        // ipv6: 0:0:0:0:0:0:0:0，也可以简写成::
        System.out.println(localhost.isAnyLocalAddress());

        // 回环地址
        // IPv4的loopback地址的范围是127.0.0.0 ~ 127.255.255.255
        // IPv6的loopback地址是0:0:0:0:0:0:0:1，也可以简写成::1
        // 虽然127.255.255.255也是loopback地址，但127.255.255.255在Windows下是无法ping通的。这是因为127.255.255.255是广播地址，在Windows下对发给广播地址的请求不做任何响应
        System.out.println(localhost.isLoopbackAddress());

        // 本地连接地址
        // IPv4的本地连接地址的范围是169.254.0.0 ~ 169.254.255.255。IPv6的本地连接地址的前12位是FE8，其他的位可以是任意取值，如FE88::
        System.out.println(localhost.isLinkLocalAddress());

        // 地区本地地址
        // IPv4的地址本地地址分为三段：10.0.0.0 ~ 10.255.255.255、172.16.0.0 ~ 172.31.255.255、192.168.0.0 ~ 192.168.255.255
        // IPv6的地区本地地址的前12位是FEC，其他的位可以是任意取值，如FED0::、FEF1::
        System.out.println(localhost.isSiteLocalAddress());

        // 广播地址
        // 通过广播地址可以向网络中的所有计算机发送信息，而不是只向一台特定的计算机发送信息
        // IPv4的广播地址的范围是224.0.0.0 ~ 239.255.255.255。IPv6的广播地址第一个字节是FF，其他的字节可以是任意值
        System.out.println(localhost.isMulticastAddress());

        // 全球范围的广播地址
        // 全球范围的广播地址可以向Internet中的所有的计算机发送信息
        // IPv4的广播地址除了224.0.0.0和第一个字节是239的IP地址都是全球范围的广播地址
        // IPv6的全球范围的广播地址中第一个字节是FF，第二个字节的范围是0E ~ FE，其他的字节可以是任意值，如FFBE::、FF0E::
        System.out.println(localhost.isMCGlobal());

        // 子网广播地址
        // 使用子网的广播地址只能向子网内的计算机发送信息
        // IPv4的子网广播地址的范围是224.0.0.0 ~ 224.0.0.255
        // IPv6的子网广播地址的第一个字节是FF，第二个字节的范围是02 ~ F2，其他的字节可以是任意值，如FFB2::、FF02:ABCD::
        System.out.println(localhost.isMCLinkLocal());

        // 本地接口广播地址
        // 本地接口广播地址不能将广播信息发送到产生广播信息的网络接口，即使是同一台计算机的另一个网络接口也不行
        // 所有的IPv4广播地址都不是本地接口广播地址
        // IPv6的本地接口广播地址的第一个字节是FF，第二个节字的范围是01 ~ F1，其他的字节可以是任意值，如FFB1::、FF01:A123::
        System.out.println(localhost.isMCNodeLocal());

        // 组织范围的广播地址
        // 使用组织范围广播地址可以向公司或企业内部的所有的计算机发送广播信息
        // IPv4的组织范围广播地址的第一个字节是239，第二个字节不小于192，第三个字节不大于195，如239.193.100.200、239.192.195.0都是组织范围广播地址
        // IPv6的组织范围广播地址的第一个字节是FF，第二个字节的范围是08 ~ F8，其他的字节可以是任意值，如FF08::、FF48::
        System.out.println(localhost.isMCOrgLocal());

        // 站点范围的广播地址
        // 使用站点范围的广播地址，可以向站点范围内的计算机发送广播信息
        // IPv4的站点范围广播地址的范围是239.255.0.0 ~ 239.255.255.255，如239.255.1.1、239.255.0.0都是站点范围的广播地址
        // IPv6的站点范围广播地址的第一个字节是FF，第二个字节的范围是05 ~ F5，其他的字节可以是任意值，如FF05::、FF45::
        System.out.println(localhost.isMCSiteLocal());
    }

    public static void v4v6() throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("::");
        int ipLen = localhost.getAddress().length;
        switch (ipLen) {
            case 4:
                // IPv4
            case 16:
                // IPv6
        }
        if (localhost instanceof Inet6Address) {
            Inet6Address address = (Inet6Address) localhost;
            // v6地址兼容v4，即只有最后四位有值，其他全为0
            System.out.println(address.isIPv4CompatibleAddress());
        }
    }

    // ---------------------------------------------------------------

    public static void interfaceUse() throws SocketException, UnknownHostException {
        NetworkInterface eth0 = NetworkInterface.getByName("eth0");// 0代表索引
        System.out.println(eth0);

        NetworkInterface baidu = NetworkInterface.getByInetAddress(InetAddress.getByName("www.baidu.com"));
        System.out.println(baidu);

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            System.out.println(networkInterfaces.nextElement());
        }

        System.out.println(eth0.getName());
        System.out.println(eth0.getDisplayName());

        Enumeration<InetAddress> inetAddresses = eth0.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
            InetAddress inetAddress = inetAddresses.nextElement();

            System.out.println(inetAddresses.nextElement());
        }

        List<InterfaceAddress> interfaceAddresses = eth0.getInterfaceAddresses();
        for (InterfaceAddress interfaceAddress : interfaceAddresses) {
            System.out.println(interfaceAddress.getBroadcast());
        }
    }

    public static void reachable() throws IOException {
        InetAddress localhost = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
        // ttl: 最大连接跃点数，路由数
        // 在Internet上使用这个方法可能会因为放火墙等因素而无法连通远程主机（实际上，远程主机是可以连通的），因此，isReachable在Internet上并不可靠。但我们可以将isReachable方法应用于局域网中
        System.out.println(localhost.isReachable(networkInterface, 10, 3000));
    }

    // ---------------------------------------------------------------

    public static void socket() throws IOException {
        Socket socket = new Socket("www.baidu.com", 80);
        OutputStream os = socket.getOutputStream();
        os.write("Hello\r\n\r\n".getBytes(StandardCharsets.UTF_8));
        os.flush(); // write写在缓存区内，默认8192，必须使用flush
        os.close();
        socket.close(); // is/os有一个关闭，网络连接也自动关闭

        System.out.println(socket.isClosed()); // 是否关闭过
        System.out.println(socket.isConnected()); // 是否连接过
        System.out.println(socket.isInputShutdown());
        System.out.println(socket.isOutputShutdown());

        socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("www.baidu.com", 80);
        socket.bind(inetSocketAddress); // 构造不传，手动绑定
        socket.connect(new InetSocketAddress("www.sina.com", 0)); // 0表示随机的 1024 ~ 65535
        System.out.println(socket.getLocalSocketAddress());
        System.out.println(socket.getRemoteSocketAddress());
    }

    public static void timeout() throws IOException {
        // 由于生产超时错误的一端都是被动端；也就是说，这一端是在接收数据，而不是发送数据。
        // 对于客户端Socket来说，只有两个地方是在接收数据；一个是在连接服务器时;另一个是在连接服务器成功后，接收服务器发过来的数据时。
        // 因此，客户端超时也分为两种类型：连接超时和读取数据超时。

        // 连接超时: 在Socket类中只有通过connect方法的第二个参数才能指定连接超时的时间
        // 读取数据超时: 通过Socket类的setSoTimeout方法来设置读取数据超时的时间；时间的单位是毫秒。这个方法必须在读取数据之前调用才会生效。如果将超时时间设为0，则不使用超时时间；也就是说，客户端什么时候和服务器断开，将完全取决于服务端程序的超时设置

        Socket socket = new Socket();
        // so -> socket options
        socket.setSoTimeout(3000); // readTimeout
        socket.connect(new InetSocketAddress("www.baidu.com", 80), 5000); // connectTimeout
        socket.close();
    }

    public static void socketInfo() throws IOException {
        Socket socket = new Socket("www.baidu.com", 80);

        // 两个合起来就是 getLocalSocketAddress
        socket.getLocalAddress(); // local ip/domain
        socket.getLocalPort(); // local port

        // 两个合起来就是 getRemoteSocketAddress
        socket.getInetAddress(); // server ip/domain
        socket.getPort(); // server port
    }

    public static void socketOptions() throws IOException {
        // see SocketOptions
        Socket socket = new Socket("www.baidu.com", 80);

        // public final static int TCP_NODELAY = 0x0001;
        // public final static int SO_REUSEADDR = 0x04;
        // public final static int SO_LINGER = 0x0080;
        // public final static int SO_TIMEOUT = 0x1006;
        // public final static int SO_SNDBUF = 0x1001;
        // public final static int SO_RCVBUF = 0x1002;
        // public final static int SO_KEEPALIVE = 0x0008;
        // public final static int SO_OOBINLINE = 0x1003;

        socket.setTcpNoDelay(false); // tcp包太小是否等待会儿再发送
        socket.setReuseAddress(false); // 是否允许多个socket绑定到相同的服务端地址，需要所有的socket都设置才有效
        socket.setSoLinger(true, 5); // close后，socket等待剩下的数据包的秒数
        socket.setSoTimeout(0); // readTimeout
        // 最好不要将输出缓冲区设得太小，否则会导致传输数据过于频繁，从而降低网络传输的效率
        socket.setSendBufferSize(8096); // 输出流的发送缓冲区大小
        socket.setReceiveBufferSize(8096); // 输入流的接收缓冲区大小
        socket.setKeepAlive(false); // 检测一下服务器是否仍处于活动状态，发送后十分钟后再次发送，如果都没响应就关闭客户端连接
        // client/server需要都开启才有效
        socket.setOOBInline(true); // 可以通过Socket类的sendUrgentData方法向服务器发送一个单字节的数据。这个单字节数据并不经过输出缓冲区，而是立即发出，int参数也只发送低位的单个字节
    }
}
