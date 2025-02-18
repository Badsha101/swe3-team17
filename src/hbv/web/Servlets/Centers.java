package hbv.example.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.function.DoubleBinaryOperator;


import org.json.*;

import hbv.example.Module.VaccinationCenters;
import hbv.example.database.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/Centers")
public class Centers extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("user_login.html");
            return;
        }

        String name = request.getParameter("name");
        String strasse = request.getParameter("strasse");
        String stadt = request.getParameter("stadt");
        String postal = request.getParameter("postal");



        VaccinationCenters vaccinationCenter = new VaccinationCenters(
                null,name,strasse,stadt,postal);



        try (Connection connection = DBUtil.getConnection()) {
            int id = DBUtil.insertVaccinationCenter(connection, vaccinationCenter);
            response.setStatus(HttpServletResponse.SC_OK);

        }  catch (Exception e) {
            e.printStackTrace(); // Für Debugging
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
