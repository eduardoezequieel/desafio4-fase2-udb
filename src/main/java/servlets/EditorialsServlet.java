package servlets;

import controllers.EditorialCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Editorial;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditorialsServlet", value = "/dashboard/inventorySelection/editorials")
public class EditorialsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EditorialCtrl editorialCtrl = new EditorialCtrl();
        String action = req.getParameter("action");


        List<Editorial> editorials = editorialCtrl.getEditorials();

        req.setAttribute("editorials", editorials);

        switch (action) {
            case "delete":
                String editorialId = req.getParameter("id");

                boolean okDelete = editorialCtrl.deleteCreator(Integer.parseInt(editorialId));

                if (okDelete) {
                    req.setAttribute("messageType", "success");
                    req.setAttribute("message", "Editorial eliminado correctamente.");
                    req.setAttribute("href", "/dashboard/inventorySelection/editorials?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);

                } else {
                    req.setAttribute("messageType", "error");
                    req.setAttribute("message", "No se pudo eliminar la editorial.");
                    req.setAttribute("href", "/dashboard/inventorySelection/editorials?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                }
                break;


            case "form":
                req.setAttribute("title", "Agregar Editorial");
                req.setAttribute("mode", "add");
                req.getRequestDispatcher("/dashboard/inventorySelection/editorials/form.jsp").forward(req, resp);
                break;

            case "index":
                req.getRequestDispatcher("/dashboard/inventorySelection/editorials/index.jsp").forward(req, resp);
                break;

            case "edit":
                String id = req.getParameter("id");
                Editorial editorial = editorialCtrl.getEditorial(Integer.parseInt(id));

                req.setAttribute("editorial", editorial);

                req.setAttribute("title", "Editar Editorial");
                req.setAttribute("mode", "edit");
                req.getRequestDispatcher("/dashboard/inventorySelection/editorials/form.jsp").forward(req, resp);
                break;


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        String name = req.getParameter("name");
        EditorialCtrl editorialCtrl = new EditorialCtrl();

        switch (mode) {
            case "add":
                boolean ok = editorialCtrl.addEditorial(name);

                if (ok) {
                    req.setAttribute("messageType", "success");
                    req.setAttribute("message", "Editorial agregado correctamente.");
                    req.setAttribute("href", "/dashboard/inventorySelection/editorials?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);

                } else {
                    req.setAttribute("messageType", "error");
                    req.setAttribute("message", "No se pudo agregar la editorial.");
                    req.setAttribute("href", "/dashboard/inventorySelection/editorials?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                }
                break;

            case "edit":
                String editorialId = req.getParameter("editorialId");
                boolean okUpdate = editorialCtrl.updateCreator(name, Integer.parseInt(editorialId));

                if (okUpdate) {
                    req.setAttribute("messageType", "success");
                    req.setAttribute("message", "Editorial actualizado correctamente.");
                    req.setAttribute("href", "/dashboard/inventorySelection/editorials?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);

                } else {
                    req.setAttribute("messageType", "error");
                    req.setAttribute("message", "No se pudo actualizar la editorial.");
                    req.setAttribute("href", "/dashboard/inventorySelection/editorials?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                }

                break;


        }


    }
}
