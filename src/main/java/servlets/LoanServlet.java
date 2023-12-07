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

@WebServlet(name = "LoanServlet", value = "/dashboard/loan")
public class LoanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        InventoryCtrl inventoryCtrl = new InventoryCtrl();
        
        List<Inventory> inventoryList = inventoryCtrl.getInventory();
        
        req.setAttribute("inventoryList", inventoryList);
        
        switch (action) {
            case "index":
                req.getRequestDispatcher("/dashboard/loan/index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedMaterials = req.getParameter("selectedMaterials");
        System.out.println(selectedMaterials);
    }
}
