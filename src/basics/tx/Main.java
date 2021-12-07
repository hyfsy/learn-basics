package basics.tx;

import java.sql.*;

/**
 * @author baB_hyf
 * @date 2021/12/07
 */
public class Main {

    public static void main(String[] args) {
        // testConnect();
        testTx();
    }

    public static void testConnect() {
        try (Connection conn = ConnectionUtil.createConnection();
             PreparedStatement ps = conn.prepareStatement("select 1 c1 from dual");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int i = rs.getInt("c1");
                System.out.println(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testTx() {
        TransactionManager.begin();
        try (Connection conn = TransactionManager.getConnection();
             Statement stat = conn.createStatement()) {
            stat.execute("insert into student (s_name) values ('测试')");
            // int i = 1 / 0;
            TransactionManager.commit();
        } catch (Exception e) {
            TransactionManager.rollback();
        } finally {
            TransactionManager.close();
        }
    }
}
