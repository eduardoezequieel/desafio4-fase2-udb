package servlets;

import controllers.CreatorCtrl;
import controllers.EditorialCtrl;
import controllers.MaterialCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Creator;
import models.Editorial;
import models.Material;
import models.MaterialType;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BaseMaterialServlet", value = "/dashboard/inventorySelection/baseMaterial")
public class BaseMaterialServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MaterialCtrl materialCtrl = new MaterialCtrl();
        CreatorCtrl creatorCtrl = new CreatorCtrl();
        EditorialCtrl editorialCtrl = new EditorialCtrl();

        List<Material> materials = materialCtrl.getMaterials();
        List<MaterialType> materialTypes = materialCtrl.getMaterialTypes();
        List<Creator> creators = creatorCtrl.getCreators();
        List<Editorial> editorials = editorialCtrl.getEditorials();

        String action = req.getParameter("action");

        req.setAttribute("materials", materials);
        req.setAttribute("materialTypes", materialTypes);
        req.setAttribute("creators", creators);
        req.setAttribute("editorials", editorials);

        switch (action) {
            case "index":
                req.getRequestDispatcher("/dashboard/inventorySelection/baseMaterial/index.jsp").forward(req, resp);
                break;
            case "create":
                req.setAttribute("mode", "add");
                req.setAttribute("title", "Crear material base");
                req.getRequestDispatcher("/dashboard/inventorySelection/baseMaterial/form.jsp").forward(req, resp);
                break;
            case "edit":
                req.setAttribute("mode", "edit");
                req.setAttribute("title", "Editar material base");
                String code = req.getParameter("code");

                Material material = materialCtrl.getMaterial(code);

                System.out.println(material.toString());

                req.setAttribute("material", material);
                req.getRequestDispatcher("/dashboard/inventorySelection/baseMaterial/form.jsp").forward(req, resp);
                break;
            case "delete":
                boolean ok = materialCtrl.deleteMaterial(req.getParameter("code"));

                if (ok) {
                    req.setAttribute("message", "Material eliminado correctamente");
                    req.setAttribute("messageType", "success");
                    req.setAttribute("href", "/dashboard/inventorySelection/baseMaterial?action=index");
                } else {
                    req.setAttribute("message", "Error al eliminar material");
                    req.setAttribute("messageType", "error");
                    req.setAttribute("href", "/dashboard/inventorySelection/baseMaterial?action=index");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MaterialCtrl materialCtrl = new MaterialCtrl();

        String title = req.getParameter("title"),
            description = req.getParameter("description"),
            materialTypeId = req.getParameter("materialType"),
            creator = req.getParameter("author"),
            editorial = req.getParameter("editorial"),
            date = req.getParameter("date"),
            duration = req.getParameter("duration"),
            songNumber = req.getParameter("songNumber"),
            mode = req.getParameter("mode");

        Material material = new Material();

        material.setTitle(title);
        material.setDescription(description);

        if (mode.equals("add")) {
            material.setMaterialTypeId(Integer.parseInt(materialTypeId));
        } else {
            Material materialFound = materialCtrl.getMaterial(req.getParameter("code"));
            material.setMaterialTypeId(materialFound.getMaterialTypeId());
        }


        switch (material.getMaterialTypeId()) {
            case 1:
            case 2:
                material.setCreatorId(Integer.parseInt(creator));
                material.setEditorialId(Integer.parseInt(editorial));
                material.setPublishDate(date);
                break;
            case 3:
                material.setEditorialId(Integer.parseInt(editorial));
                material.setPublishDate(date);
                break;
            case 4:
                material.setDuration(duration);
                material.setSongNumber(Integer.parseInt(songNumber));
                break;
        }

        if (mode.equals("add")) {
            boolean ok = materialCtrl.createMaterial(material);
            if (ok) {
                req.setAttribute("message", "Material creado correctamente");
                req.setAttribute("messageType", "success");
                req.setAttribute("href", "/dashboard/inventorySelection/baseMaterial?action=index");
                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Error al crear el material");
                req.setAttribute("messageType", "error");
                req.setAttribute("href", "/dashboard/inventorySelection/baseMaterial?action=index");
                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            }

        } else {
            String code = req.getParameter("code");
            material.setCode(code);
            boolean ok = materialCtrl.updateMaterial(material);

            if (ok) {
                req.setAttribute("message", "Material actualizado correctamente");
                req.setAttribute("messageType", "success");
                req.setAttribute("href", "/dashboard/inventorySelection/baseMaterial?action=index");
                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Error al actualizar el material");
                req.setAttribute("messageType", "error");
                req.setAttribute("href", "/dashboard/inventorySelection/baseMaterial?action=index");
                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            }
        }
    }
}
