package basics.tx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author baB_hyf
 * @date 2021/12/07
 */
public class ConnectionUtil {

    public static final String DRIVER   = "com.mysql.cj.jdbc.Driver";
    public static final String URL      = "jdbc:mysql://localhost:3306/learn?serverTimezone=UTC&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "hyflearn";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("cannot find driver: " + DRIVER, e);
        }
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
