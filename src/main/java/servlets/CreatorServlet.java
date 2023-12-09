package servlets;

import controllers.CreatorCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Creator;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreatorServlet", value = "/dashboard/inventorySelection/creator")
public class CreatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreatorCtrl creatorCtrl = new CreatorCtrl();
        String action = req.getParameter("action");

        List<Creator> creators = creatorCtrl.getCreators();

        req.setAttribute("creators", creators);

        switch (action) {
            case "form":
                req.setAttribute("title", "Agregar creador");
                req.setAttribute("mode", "add");
                req.getRequestDispatcher("/dashboard/inventorySelection/creator/form.jsp").forward(req, resp);
                break;
            case "edit":
                String id = req.getParameter("id");
                System.out.println(id);
                Creator creator = creatorCtrl.getCreator(Integer.parseInt(id));

                req.setAttribute("creator", creator);

                req.setAttribute("title", "Editar creador");
                req.setAttribute("mode", "edit");
                req.getRequestDispatcher("/dashboard/inventorySelection/creator/form.jsp").forward(req, resp);
                break;
            case "delete":
                String creatorId = req.getParameter("id");

                boolean okDelete = creatorCtrl.deleteCreator(Integer.parseInt(creatorId));

                if (okDelete) {
                    req.setAttribute("messageType", "success");
                    req.setAttribute("message", "Creador eliminado correctamente.");
                    req.setAttribute("href", "/dashboard/inventorySelection/creator?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                } else {
                    req.setAttribute("messageType", "error");
                    req.setAttribute("message", "No se pudo eliminar al creador. Por favor contacte con el administrador.");
                    req.setAttribute("href", "/dashboard/inventorySelection/creator?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                }
                break;
            case "index":
                req.getRequestDispatcher("/dashboard/inventorySelection/creator/index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreatorCtrl creatorCtrl = new CreatorCtrl();
        String mode = req.getParameter("mode");
        String name = req.getParameter("name");

        switch (mode) {
            case "add":
                boolean ok = creatorCtrl.addCreator(name);

                if (ok) {
                    req.setAttribute("messageType", "success");
                    req.setAttribute("message", "Creador agregado correctamente.");
                    req.setAttribute("href", "/dashboard/inventorySelection/creator?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                } else {
                    req.setAttribute("messageType", "error");
                    req.setAttribute("message", "No se pudo agregar al creador. Por favor contacte con el administrador.");
                    req.setAttribute("href", "/dashboard/inventorySelection/creator?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                }
                break;
            case "edit":
                String creatorId = req.getParameter("creatorId");
                boolean okUpdate = creatorCtrl.updateCreator(name, Integer.parseInt(creatorId));

                if (okUpdate) {
                    req.setAttribute("messageType", "success");
                    req.setAttribute("message", "Creador actualizado correctamente.");
                    req.setAttribute("href", "/dashboard/inventorySelection/creator?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                } else {
                    req.setAttribute("messageType", "error");
                    req.setAttribute("message", "No se pudo actualizar al creador. Por favor contacte con el administrador.");
                    req.setAttribute("href", "/dashboard/inventorySelection/creator?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                }
                break;
        }


    }
}
