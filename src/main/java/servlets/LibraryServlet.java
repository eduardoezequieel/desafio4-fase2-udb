package servlets;

import controllers.InventoryCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Inventory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LibraryServlet", value = "/library")
public class LibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InventoryCtrl inventoryCtrl = new InventoryCtrl();

        List<Inventory> inventoryList = inventoryCtrl.getInventory();

        req.setAttribute("inventoryList", inventoryList);

        req.getRequestDispatcher("/library/index.jsp").forward(req, resp);
    }
}
