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

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public static int insertVaccinationCenter(Connection connection, VaccinationCenters vaccinationCenter)
            throws SQLException {


        var pss = connection.prepareStatement(
                "INSERT INTO impfzentren(name, street, city, postalcode) VALUES(?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        pss.setString(1, vaccinationCenter.getName());
        pss.setString(2, vaccinationCenter.getStrasse());
        pss.setString(3, vaccinationCenter.getStadt());
        pss.setString(4, vaccinationCenter.getPostal());
        pss.executeUpdate(); // Ändere zu executeUpdate()
        try (var rs = pss.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1); // Generierte ID zurückgeben
            }
        }

        return -1; // Falls keine ID generiert wurde
    }

    public static List<VaccinationCenters> Centerliste(Connection connection) throws SQLException {
        var pss = connection.prepareStatement("SELECT * FROM impfzentren");
        var rs = pss.executeQuery();
        var Vaccination_Centers = new ArrayList<VaccinationCenters>();
        while (rs.next()) {
            var VaccinationCenters = new VaccinationCenters(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("postalcode"));
            Vaccination_Centers.add(VaccinationCenters);
        }
        return Vaccination_Centers;
    }

    public static int insertVaccines(Connection connection, Vaccine vaccine)
            throws SQLException {

        String Query= "Insert into Impfung(Name,Anzahl) values(?,?)";
        var pss = connection.prepareStatement(Query,
                Statement.RETURN_GENERATED_KEYS);
        pss.setString(1, vaccine.getName());
        pss.setString(2, vaccine.getCount());

        pss.executeUpdate(); // Ändere zu executeUpdate()
        try (var rs = pss.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1); // Generierte ID zurückgeben
            }
        }

        return -1; // Falls keine ID generiert wurde
    }


    public static List<Vaccine>Vaccinesliste(Connection connection) throws SQLException {
        var pss = connection.prepareStatement("SELECT * FROM Impfung");
        var rs = pss.executeQuery();
        var Vaccines = new ArrayList<Vaccine>();
        while (rs.next()) {
            var Vaccine = new Vaccine(
                    rs.getInt("id"),
                    rs.getString("Name"),
                    rs.getString("Anzahl"));
            Vaccines.add(Vaccine);
        }
        return Vaccines;
    }


    public static List<VaccinationCenters> Centerlist(Connection con) throws SQLException {
        List<VaccinationCenters> vaccinationCentersList = new ArrayList<>();

        String query = "SELECT * FROM impfzentren"; // SQL-Abfrage für die Impfzentren
        try (PreparedStatement pss = con.prepareStatement(query);
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

        return vaccinationCentersList; // Gib die Liste mit den Impfzentren zurück
    }



    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Database connection test successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
