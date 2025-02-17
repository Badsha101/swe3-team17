package hbv.web.db;

public class Queries {

    public static final String SELECT_ALL_CENTERS= "SELECT * FROM vaccinationcenter vc";
    public static final String SELECT_ALL_VACCINES= "SELECT * FROM vaccines v";
    public static final String SELECT_VACCINE_BY_ID = "SELECT * FROM vaccines where center_id = ?";
    public static final String SELECT_TIMESLOT_BY_ID = "SELECT * FROM timeslots where center_id = ?";

    public static final String SELECT_ALL_CENTERS_WITH_VACCINES= "SELECT vc.id AS center_id, vc.name AS center_name, v.id AS vaccine_id, v.name AS vaccine_name\n" +
            "FROM vaccinationcenter vc\n" +
            "INNER JOIN vaccines v ON vc.id = v.center_id;\n";

    public static final String INSERT_USER = "INSERT INTO users (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
    public static final String INSERT_RELATIVE= "INSERT INTO relatives (user_id, firstname, lastname) VALUES (?, ?, ?)";

    public static final String RELATIVE_APPOINTMENT = "INSERT INTO appointment (user_id, relative_id, timeslot_id, vaccine_id, center_id, booking_date) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String USER_APPOINTMENT = "INSERT INTO appointment (user_id,  timeslot_id, vaccine_id, center_id, booking_date) VALUES (?, ?, ?, ?, ?)";


    //    public static final String USER_APPOINTMENT  = "INSERT INTO appointments (user_id, vaccination_centre, vaccine, date, time) VALUES (?, ?, ?, ?, ?)";
//        public static final String INSERT_APPOINTMENT = "INSERT INTO appointments (userId, relativeId, vaccinationCentre, vaccine, date, time) VALUES (?, ?, ?, ?, ?, ?)";
//        public static final String UPDATE_APPOINTMENT = "UPDATE appointments SET userId=?, relativeId=?, vaccinationCentre=?, vaccine=?, date=?, time=? WHERE id=?";
    public static final String DELETE_APPOINTMENT = "DELETE FROM appointment WHERE id=?";
    public static final String SELECT_ALL_APPOINTMENTS = "SELECT\n" +
            "    a.id,\n" +
            "    COALESCE(r.firstname, u.firstname) AS patient_name,\n" +
            "    COALESCE(r.lastname, u.lastname) AS patient_lastname,\n" +
            "    a.vaccination_centre,\n" +
            "    a.vaccine,\n" +
            "    a.date,\n" +
            "    a.time\n" +
            "\n" +
            "\n" +
            "\n" +
            "FROM appointments a\n" +
            "         LEFT JOIN users u ON a.user_id = u.id\n" +
            "         LEFT JOIN relatives r ON a.relative_id = r.id\n" +
            "WHERE u.id = ?;\n";
    public static final String SELECT_APPOINTMENT_BY_ID = "SELECT * FROM appointment_view WHERE id=?";
    public static final String COUNT_APPOINTMENT = "SELECT COUNT(*) AS appointment_count FROM appointment WHERE user_id = ?";
}









