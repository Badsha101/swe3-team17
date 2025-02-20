package hbv.web.db;

import hbv.web.Module.VaccinationCenters;
import hbv.web.Module.Vaccine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/swe3team17db";
    private static final String USERNAME = "swe3team17";
    private static final String PASSWORD = "@team17";

    // Load MySQL Driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver NOT found! Error: " + e.getMessage());
            e.printStackTrace();

        }
    }

    // Get database connection
    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to database...");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connected successfully!");
        return connection;
    }

    // Insert Vaccination Center
    public static int insertVaccinationCenter(Connection connection, VaccinationCenters vaccinationCenter)
            throws SQLException {
        String query = "INSERT INTO Impfzentren(name, street, city, postalcode) VALUES(?,?,?,?)";
        try (PreparedStatement pss = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pss.setString(1, vaccinationCenter.getName());
            pss.setString(2, vaccinationCenter.getStrasse());
            pss.setString(3, vaccinationCenter.getStadt());
            pss.setString(4, vaccinationCenter.getPostal());
            pss.executeUpdate();

            try (ResultSet rs = pss.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Return generated ID
                }
            }
        }
        return -1; // If no ID was generated
    }

    // Get list of Vaccination Centers
    public static List<VaccinationCenters> getVaccinationCenters(Connection connection) throws SQLException {
        List<VaccinationCenters> vaccinationCentersList = new ArrayList<>();
        String query = "SELECT * FROM impfzentren";

        try (PreparedStatement pss = connection.prepareStatement(query);
             ResultSet rs = pss.executeQuery()) {
            while (rs.next()) {
                VaccinationCenters center = new VaccinationCenters(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("postalcode")
                );
                vaccinationCentersList.add(center);
            }
        }
        return vaccinationCentersList;
    }

    // Insert Vaccines
    public static int insertVaccines(Connection connection, Vaccine vaccine) throws SQLException {
        String query = "INSERT INTO Impfung(Name, Anzahl) VALUES(?,?)";
        try (PreparedStatement pss = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pss.setString(1, vaccine.getName());
            pss.setInt(2, vaccine.getCount()); // Change to setInt()
            pss.executeUpdate();

            try (ResultSet rs = pss.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    // Get list of Vaccines
    public static List<Vaccine> getVaccines(Connection connection) throws SQLException {
        List<Vaccine> vaccineList = new ArrayList<>();
        String query = "SELECT * FROM impfung";

        try (PreparedStatement pss = connection.prepareStatement(query);
             ResultSet rs = pss.executeQuery()) {
            while (rs.next()) {
                Vaccine vaccine = new Vaccine(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getInt("Anzahl")
                );
                vaccineList.add(vaccine);
            }
        }
        return vaccineList;
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println(" Database connection test successful!");
        } catch (SQLException e) {
            System.err.println(" Connection failed: " + e.getMessage());
        }
    }
}
