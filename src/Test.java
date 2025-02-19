import hbv.web.db.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded successfully!");

            // Attempt connection (Update with your DB details)
            String url = "jdbc:mysql://localhost:3306/swe3team17db";
            String user = "swe3team17";
            String password = "@team17";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connection successful!");

            // Close connection
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver NOT found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}
