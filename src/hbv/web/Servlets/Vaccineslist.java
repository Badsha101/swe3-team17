package hbv.example.Servlets;


import hbv.example.Module.Vaccine;
import hbv.example.database.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.*;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/Vaccineslist")
public class Vaccineslist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray allVaccinesJson;
        List<Vaccine> allVaccines;
        try (Connection con = DBUtil.getConnection()) {
            allVaccines = DBUtil.Vaccinesliste(con);
        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal Error");
            return;
        }
        allVaccinesJson = new JSONArray();
        for (var vc : allVaccines) {
            JSONObject json = new JSONObject();
            json.put("id", vc.getId());
            json.put("name", vc.getName());
            json.put("Count", vc.getCount());

            allVaccinesJson.put(json);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(allVaccinesJson);
    }

}
