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
        for (UserType userType : twoUserTypes
        ) {
            System.out.println(userType.getUserTypeId());

        }

        switch (action) {
            case "edit":
                String userTypeName = req.getParameter("userTypeName");
                String allowedBorrowedMaterials = req.getParameter("allowedBorrowedMaterials");

                req.setAttribute("userTypeName", userTypeName);
                req.setAttribute("allowedBorrowedMaterials", allowedBorrowedMaterials);

                req.setAttribute("title", "Editar Cantidad de Prestamos");
                req.setAttribute("mode", "edit");
                req.getRequestDispatcher("/dashboard/settingsSelection/allowedBorrowedMaterials/form.jsp").forward(req, resp);
                break;
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

        PermisosCtrl permisosCtrl = new PermisosCtrl();
        permisosCtrl.actualizarMaterialesPermitidos(userTypeName, Integer.parseInt(allowedBorrowedMaterials));

        req.setAttribute("messageType", "success");
        req.setAttribute("message", "Cantidad de Prestamos actualizados correctamente.");
        req.setAttribute("href", "/dashboard/settingsSelection/allowedBorrowedMaterials?action=index");
        req.getRequestDispatcher("/components/message.jsp").forward(req, resp);

    }
}