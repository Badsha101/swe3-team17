package hbv.example.Servlets;


import java.io.IOException;
import java.sql.*;
import java.util.List;

import hbv.example.database.DBUtil;
import org.json.*;

import hbv.example.Module.VaccinationCenters;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/Centerliste")
public class Centerliste extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray allCentersJson;
        List<VaccinationCenters> allCenters;
        try (Connection con = DBUtil.getConnection()) {
            allCenters = DBUtil.Centerliste(con);
        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal Error");
            return;
        }
        allCentersJson = new JSONArray();
        for (var vc : allCenters) {
            JSONObject json = new JSONObject();
            json.put("id", vc.getId());
            json.put("name", vc.getName());
            json.put("strasse", vc.getStrasse());
            json.put("stadt", vc.getStadt());
            json.put("postal", vc.getPostal());
            allCentersJson.put(json);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(allCentersJson);
    }

}