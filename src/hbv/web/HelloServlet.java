package hbv.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Get the output stream to send a response to the client
        PrintWriter out = response.getWriter();

        // Generate the response
        out.println("<html><body>");
        out.println("<h1>Hello, Jakarta Servlet!</h1>");
        out.println("</body></html>");
    }
}

