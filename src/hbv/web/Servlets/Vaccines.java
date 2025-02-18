package hbv.example.Servlets;



import hbv.example.Module.Vaccine;
import hbv.example.database.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/Vaccines")
public class Vaccines extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("user_login.html");
            return;
        }

        String name = request.getParameter("Name");
        String Count = request.getParameter("Count");



        Vaccine vaccine= new Vaccine(null,name,Count);

        try(Connection conn=DBUtil.getConnection()){
      int id =   DBUtil.insertVaccines(conn,vaccine);
            response.setStatus(HttpServletResponse.SC_OK);

        }  catch (Exception e) {
            e.printStackTrace(); // Für Debugging
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

