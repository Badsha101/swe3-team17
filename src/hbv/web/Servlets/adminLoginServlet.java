package hbv.web.Servlets;

import hbv.web.Dao.Admindao;
import hbv.web.Module.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class adminLoginServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String email = request.getParameter("email");
      String password = request.getParameter("password");

      Admindao admindao=new Admindao();
      Admin admin= admindao.isValidAdmin(email,password);

      if(admin!=null){
      HttpSession session = request.getSession();
      session.setAttribute("admin", admin);

      RequestDispatcher dispatcher = request.getRequestDispatcher("Admin_dashboard.html");
      dispatcher.forward(request, response);
      System.out.println("Success");


  } else {

        response.sendRedirect("adminLogin.html?error=1");


    }
}
}


