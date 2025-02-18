package hbv.web.Servlets;

import hbv.web.Dao.Admindao;
import hbv.web.Module.Admin;
import hbv.web.db.DBUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
//
//        if(email.equals("ok@gmail.com")){
//            response.sendRedirect("https://youtube.com");

  } else {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("user_login.html?error=1");
//            dispatcher.forward(request, response);
        response.sendRedirect("admin_login.html?error=1");


    }
}
}


