package servlets;

import controllers.InventoryCtrl;
import controllers.LoanCtrl;
import controllers.UsersCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Inventory;
import models.Loan;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
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
            default:
                req.getRequestDispatcher("/dashboard/loan/index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedMaterials = req.getParameter("selectedMaterials"),
                startDate = req.getParameter("startDate"),
                endDate = req.getParameter("endDate"),
                email = req.getParameter("email");

        System.out.println(selectedMaterials + " " + startDate + " " + endDate + " " + email);

        String[] selectedMaterialsArray = selectedMaterials.split(",");
        UsersCtrl usersCtrl = new UsersCtrl();
        User loggedUser = usersCtrl.getLoggedUser(email);

        LoanCtrl loanController = new LoanCtrl();

        int hasPendingLoan = loanController.checkIfUserHasAPendingLoan(loggedUser.getIdentificationCode());

        if (hasPendingLoan == 1) {
            req.setAttribute("message", "Usted posee un prestamo activo. No podrá efectuar otro hasta que no finalice el actual.");
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/dashboard/loan?action=index");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        } else if (hasPendingLoan == -1) {
            req.setAttribute("message", "Ha ocurrido un error al verificar si tiene un prestamo activo. Contacte al administrador.");
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/dashboard/loan?action=index");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        }

        List<String> notAllowedMaterials = new ArrayList<>();

        for (String materialId : selectedMaterialsArray) {
            int response = loanController.materialIsAlreadyBorrowed(Integer.parseInt(materialId));

            if (response == -1) {
                req.setAttribute("message", "Ha ocurrido un error al verificar si los materiales estan disponibles. Contacte al administrador.");
                req.setAttribute("messageType", "error");
                req.setAttribute("href", "/dashboard/loan?action=index");
                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                return;
            }
            ;

            if (response == 1) {
                notAllowedMaterials.add(materialId);
            }
        }

        if (!notAllowedMaterials.isEmpty()) {
            StringBuilder ids = new StringBuilder();

            for (String notAllowedMaterial : notAllowedMaterials) {
                ids.append(notAllowedMaterial).append(" ");
            }

            req.setAttribute("message", "Los materiales con los siguientes ids no estan disponibles: " + ids);
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/dashboard/loan?action=index");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        }

        int borrowedMaterials = loanController.getBorrowedMaterialsQuantity(loggedUser.getIdentificationCode());

        if (borrowedMaterials + selectedMaterialsArray.length > loggedUser.getAllowedBorrowedMaterials() && !loggedUser.getUserTypeName().equals("Administrador")) {
            req.setAttribute("message", "Ha sobrepasado el limite de materiales disponibles. " + "Prestamo actual: " + selectedMaterialsArray.length + ", Otros prestamos:  " + borrowedMaterials);
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/dashboard/loan?action=index");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        }

        Loan loan = new Loan();

        loan.setUserId(loggedUser.getIdentificationCode());
        loan.setStartDate(startDate);
        loan.setEndDate(endDate);

        boolean loanResponse = loanController.createLoan(loan);

        if (!loanResponse) {
            req.setAttribute("message", "Ha ocurrido un error al crear el prestamo. Contacte al administrador.");
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/dashboard/loan?action=index");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        }
        ;

        int loanId = loanController.getLatestLoan();

        if (loanId == -1) {
            req.setAttribute("message", "Ha ocurrido un error al obtener el id de su ultimo prestamo. Contacte al administrador.");
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/dashboard/loan?action=index");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        }

        for (String selectedItem : selectedMaterialsArray) {
            boolean ok = loanController.addItemToLoan(loanId, Integer.parseInt(selectedItem));

            if (!ok) {
                req.setAttribute("message", "Ha ocurrido un error al agregar un material a su prestamo. Contacte al administrador.");
                req.setAttribute("messageType", "error");
                req.setAttribute("href", "/dashboard/loan?action=index");
                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                return;
            }
        }

        req.setAttribute("message", "Prestamo creado correctamente");
        req.setAttribute("messageType", "success");
        req.setAttribute("href", "/dashboard/loan?action=index");
        req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
    }
}
