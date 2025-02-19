package hbv.web.Servlets;

import hbv.web.Dao.Userdao;
import hbv.web.Module.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class registerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password1");  // Corrected name to match the form
        String password2 = request.getParameter("password2");

        Userdao userdao = new Userdao();

        try {
            // Check if email already exists
            if (userdao.isEmail(email)) {
                response.sendRedirect("register.html?error=emailexists");
                return;
            }

            // Check if passwords match
            if (!password.equals(password2)) {
                response.sendRedirect("register.html?error=passwordmismatch");
                return;
            }

            // Create a new user object and attempt to add it to the database
            User user = new User(email, password, firstname, lastname);
            int userAdded = userdao.addUser(user);

            if (userAdded > 0) {
                response.sendRedirect("register.html?error=success"); // Successful registration
            } else {
                response.sendRedirect("register.html?error=1"); // Generic failure
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.html?error=1");
        }
    }
}
