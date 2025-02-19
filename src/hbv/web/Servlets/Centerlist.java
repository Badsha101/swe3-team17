package hbv.web.Servlets;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import hbv.web.db.DBUtil;
import org.json.*;

import hbv.web.Module.VaccinationCenters;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Centerlist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray allCentersJson;
        List<VaccinationCenters> allCenters;

        try (Connection con = DBUtil.getConnection()) {
            allCenters = DBUtil.getVaccinationCenters(con);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal Error");
            return;
        }

        allCentersJson = new JSONArray();
        for (VaccinationCenters vc : allCenters) {
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
