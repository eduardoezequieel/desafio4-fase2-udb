package servlets;

import controllers.InventoryCtrl;
import controllers.MaterialCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Inventory;
import models.Library;
import models.Material;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "InventoryServlet", value = "/dashboard/inventorySelection/inventory")
public class InventoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        InventoryCtrl inventoryCtrl = new InventoryCtrl();
        MaterialCtrl materialCtrl = new MaterialCtrl();

        List<Library> libraries = inventoryCtrl.getLibraries();
        List<Inventory> inventories = inventoryCtrl.getInventory();
        List<Material> materials = materialCtrl.getMaterials();

        req.setAttribute("inventories", inventories);
        req.setAttribute("materials", materials);
        req.setAttribute("libraries", libraries);

        switch(action) {
            case "index":
                req.getRequestDispatcher("/dashboard/inventorySelection/inventory/index.jsp").forward(req, resp);
                break;
            case "create":
                req.setAttribute("mode", "create");
                req.setAttribute("title", "Agregar al inventario");
                req.getRequestDispatcher("/dashboard/inventorySelection/inventory/form.jsp").forward(req, resp);
                break;

            case "edit":
                int inventoryId = Integer.parseInt(req.getParameter("inventoryId"));
                Inventory inventory = inventoryCtrl.getInventoryItem(inventoryId);
                req.setAttribute("inventory", inventory);
                req.setAttribute("mode", "edit");
                req.setAttribute("title", "Editar inventario");
                req.getRequestDispatcher("/dashboard/inventorySelection/inventory/form.jsp").forward(req, resp);
                break;
        }
    }
}
