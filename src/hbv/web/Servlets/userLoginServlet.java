package hbv.web.Servlets;


import hbv.web.Dao.Userdao;
import hbv.web.Module.User;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/userLoginServlet")
public class userLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

       Userdao userdao = new Userdao();
        User user= userdao.isValidUser(email,password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("buchen.html");
            dispatcher.forward(request, response);
            System.out.println("Success");

//        if(email.equals("ok@gmail.com")){
//            response.sendRedirect("https://youtube.com");

        } else {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("user_login.html?error=1");
//            dispatcher.forward(request, response);
            response.sendRedirect("user_login.html?error=1");


        }
    }
}








