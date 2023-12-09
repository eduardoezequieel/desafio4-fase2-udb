package servlets;

import controllers.LoanCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Loan;
import models.Material;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "ReturnServlet", value = "/dashboard/return")
public class ReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoanCtrl loanCtrl = new LoanCtrl();

        String action = req.getParameter("action");
        int loanId = -1;

        List<Loan> activeLoans = loanCtrl.getActiveLoans();
        req.setAttribute("activeLoans", activeLoans);

        switch (action) {
            case "index":
                req.getRequestDispatcher("/dashboard/return/index.jsp").forward(req, resp);
                break;
            case "details":
                loanId = Integer.parseInt(req.getParameter("id"));
                List<Material> materials = loanCtrl.getMaterialsFromLoan(loanId);
                req.setAttribute("materials", materials);
                req.setAttribute("title", "Detalles del prestamo " + loanId);
                req.getRequestDispatcher("/dashboard/return/details.jsp").forward(req, resp);
                break;
            case "finish":
                Loan loan = loanCtrl.getLoan(Integer.parseInt(req.getParameter("id")));
                boolean feeWasExecuted = false;
                double finalFee = 0.00;
                double currentFee = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate endDate = LocalDate.parse(loan.getEndDate(), formatter);

                LocalDate actualDate = LocalDate.now();

                long difference = java.time.temporal.ChronoUnit.DAYS.between(endDate, actualDate);

                if (difference > 0) {
                    String currentYear = loan.getEndDate().substring(0, 4);

                    currentFee = loanCtrl.getCurrentFee(currentYear);

                    finalFee = currentFee * difference;

                    boolean ok = loanCtrl.addFeeToLoan(loan.getLoanId(), finalFee, difference);

                    if (!ok) return;

                    feeWasExecuted = true;
                }

                boolean ok = loanCtrl.finishLoan(loan.getLoanId());

                if (!ok) {
                    req.setAttribute("message", "Error al finalizar prestamo.");
                    req.setAttribute("messageType", "error");
                    req.setAttribute("href", "/dashboard/return?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                    return;
                }

                if (!feeWasExecuted) {
                    req.setAttribute("message", "Prestamo finalizado exitosamente.");
                    req.setAttribute("messageType", "success");
                    req.setAttribute("href", "/dashboard/return?action=index");
                } else {
                    DecimalFormat df = new DecimalFormat("#.00");
                    req.setAttribute("message", "Prestamo finalizado exitosamente. Se aplico una mora diaria de $" + df.format(currentFee) + " por " + difference + " dias de retraso. ($" + df.format(finalFee) + ")");
                    req.setAttribute("messageType", "success");
                    req.setAttribute("href", "/dashboard/return?action=index");
                }

                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);

                break;
            case "approve":
                loanId = Integer.parseInt(req.getParameter("id"));
                String code = req.getParameter("code");

                System.out.println(code);

                boolean approved = loanCtrl.approveLoan(loanId, code);

                if (approved) {
                    req.setAttribute("message", "Prestamo aprobado correctamente");
                    req.setAttribute("messageType", "success");
                    req.setAttribute("href", "/dashboard/return?action=index");
                } else {
                    req.setAttribute("message", "Error al aprobar prestamo.");
                    req.setAttribute("messageType", "error");
                    req.setAttribute("href", "/dashboard/return?action=index");
                }

                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }
}
