package hbv.example.database;

import hbv.example.Module.VaccinationCenters;
import hbv.example.Module.Vaccine;

import javax.sound.midi.VoiceStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBUtil {


    private static final String URL = "jdbc:mysql://localhost:3307/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "@Subedi1";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
//
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws Exception {
        System.out.println("Connecting to database");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
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

}


