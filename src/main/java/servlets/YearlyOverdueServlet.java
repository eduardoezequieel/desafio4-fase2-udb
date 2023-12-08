package servlets;

import controllers.PermisosCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "YearlyOverdueServlet", value = "/dashboard/settingsSelection/yearlyOverdue")
public class YearlyOverdueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        PermisosCtrl permisosCtrl = new PermisosCtrl();

        List<Object[]> results = permisosCtrl.obtenerResultadosYearlyOverdue();

        req.setAttribute("results", results);

        switch (action) {
            case "index":
                req.getRequestDispatcher("/dashboard/settingsSelection/yearlyOverdue/index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String year = req.getParameter("year");
        String cost = req.getParameter("cost");
        System.out.println(year);
        System.out.println(cost);


    }
}
