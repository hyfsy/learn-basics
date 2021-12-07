package basics.tx;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author baB_hyf
 * @date 2021/12/05
 */
public class TransactionManager {

    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public static void begin() {
        Connection old = connectionThreadLocal.get();
        if (old != null) {
            return;
        }

        try {
            Connection conn = ConnectionUtil.createConnection();
            conn.setAutoCommit(false);
            connectionThreadLocal.set(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit() {
        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            throw new IllegalStateException("Connection not bind");
        }

        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            throw new IllegalStateException("Connection not bind");
        }

        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            return;
        }

        try {
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionThreadLocal.remove();
        }
    }

    public static Connection getConnection() {
        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            try {
                conn = ConnectionUtil.createConnection();
            } catch (SQLException e) {
                throw new RuntimeException("Create connection failed");
            }
        }
        return conn;
    }
}
