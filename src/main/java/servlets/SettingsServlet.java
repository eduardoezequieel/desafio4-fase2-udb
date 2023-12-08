package servlets;

import controllers.PermisosCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.UserType;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SettingsServlet", value = "/dashboard/settingsSelection/allowedBorrowedMaterials")
public class SettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        PermisosCtrl permisosCtrl = new PermisosCtrl();

        List<UserType> twoUserTypes = permisosCtrl.getTwoUsergetTypes();

        req.setAttribute("twoUserTypes", twoUserTypes);

        switch (action) {
            case "index":
                req.getRequestDispatcher("/dashboard/settingsSelection/allowedBorrowedMaterials/index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userTypeName = req.getParameter("userTypeName");
        String allowedBorrowedMaterials = req.getParameter("allowedBorrowedMaterials");
        System.out.println(userTypeName);
        System.out.println(allowedBorrowedMaterials);
    }
}