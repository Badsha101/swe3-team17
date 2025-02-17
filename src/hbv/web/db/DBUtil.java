package hbv.web.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://mysql-server:3306/swe3team17db";
    private static final String USER = "swe3team17";
    private static final String PASSWORD = "password";

    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 100;

    private static final List<Connection> connectionPool = new ArrayList<>(MAX_POOL_SIZE);

    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }

        // Initialize connection pool
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                System.out.println("Connecting to database...");
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                connectionPool.add(connection);
                System.out.println("connected to database successfully");
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static Connection getConnection() {
        synchronized (connectionPool) {
            if (!connectionPool.isEmpty()) {
                return connectionPool.remove(0);
            } else if (connectionPool.size() < MAX_POOL_SIZE) {
                try {
                    return DriverManager.getConnection(DB_URL, USER, PASSWORD);
                } catch (SQLException e) {
                    throw new RuntimeException("Fehler beim Abrufen der Datenbankverbindung", e);
                }
            }
        }
        throw new RuntimeException("Timeout beim Warten auf eine Datenbankverbindung");
    }

    public static void releaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        synchronized (connectionPool) {
            if (connectionPool.size() < MAX_POOL_SIZE) {
                connectionPool.add(connection);
            } else {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Fehler beim Freigeben der Datenbankverbindung", e);
                }
            }
        }
    }
}
