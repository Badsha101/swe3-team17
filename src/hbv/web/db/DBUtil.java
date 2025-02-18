package hbv.web.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/swe3team17db";
    private static final String USERNAME = "swe3team17";
    private static final String PASSWORD = "@team17";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Lade den MySQL-Treiber
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("MySQL JDBC Driver not found!");
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to database...");
        Connection connection = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connected successfully!");
        return connection;
    }


    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Database connection test successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
