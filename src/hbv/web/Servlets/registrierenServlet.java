package hbv.example.Servlets;

import hbv.example.Dao.Userdao;
import hbv.example.Module.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.*;
import java.io.IOException;

@WebServlet("/registrierenServlet")
public class registrierenServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("vname");
        String lastname = request.getParameter("nname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        Userdao userdao = new Userdao();
        User user = new User(email, password, password2, firstname, lastname);
        int Useradd = -1;

        try {
            if (userdao.isEmail(email)) {
                response.sendRedirect("registrieren.html?error=2");
            } else if (!password.equals(password2)) {
                response.sendRedirect("registrieren.html?error=4");

            } else {
                Useradd = userdao.addUser(user);

                if (Useradd > 0) {
                    response.sendRedirect("registrieren.html?error=3"); // Weiterleitung bei Erfolg
                } else {
                    response.sendRedirect("registrieren.html?error=1"); // Weiterleitung bei Fehler
                }

                }
            }catch(Exception e){
                throw new RuntimeException(e);
            }


        }
    }



