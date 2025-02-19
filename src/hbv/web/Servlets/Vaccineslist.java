package hbv.web.Servlets;

import hbv.web.Module.Vaccine;
import hbv.web.db.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.json.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Vaccineslist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray allVaccinesJson;
        List<Vaccine> allVaccines;

        try (Connection con = DBUtil.getConnection()) {
            allVaccines = DBUtil.getVaccines(con); // ✅ FIXED method name
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Internal Error");
            return;
        }

        allVaccinesJson = new JSONArray();
        for (Vaccine vc : allVaccines) {
            JSONObject json = new JSONObject();
            json.put("id", vc.getId());
            json.put("name", vc.getName());
            json.put("count", vc.getCount());

            allVaccinesJson.put(json);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(allVaccinesJson);
    }
}
