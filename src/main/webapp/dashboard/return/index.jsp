<%@ page import="models.Loan" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Dashboard | Devoluciones</title>

    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="return.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <h1 class="text-center">Devoluciones</h1>
            <div class="table-container">
                <table class="table table-striped" style="width: 100%" id="material-table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Responsable</th>
                        <th scope="col">Administrador</th>
                        <th scope="col">Inicio</th>
                        <th scope="col">Finalización</th>
                        <th scope="col">Mora</th>
                        <th scope="col">Días de retraso</th>
                        <th scope="col">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Loan> activeLoans = (List<Loan>) request.getAttribute("activeLoans");
                        for (Loan loan : activeLoans) {
                    %>
                    <tr>
                        <th scope="row"><%= loan.getLoanId() %>
                        </th>
                        <td><%= loan.getLoanStateName() %>
                        </td>
                        <td><%= loan.getResponsable() %>
                        </td>
                        <td><%= loan.getAdmin() %>
                        </td>
                        <td><%= loan.getStartDate() %>
                        </td>
                        <td><%= loan.getEndDate() %>
                        </td>
                        <td><%=loan.getPrice() == null ? "$0.00" : "$" + loan.getPrice()%>
                        </td>
                        <td><%= loan.getPendingDays() %>
                        </td>
                        <td>
                            <a style="width: min-content;"
                               href="/dashboard/return?action=details&id=<%=loan.getLoanId()%>">
                                <button class="btn btn-primary btn-sm">Ver detalle</button>
                            </a>
                            <a style="width: min-content"
                               class="<%=loan.getLoanStateName().equals("Pendiente de aprobación") ? "" : "d-none"%> <%=loan.getLoanStateName().equals("Finalizado") ? "d-none" : ""%>"
                               href="/dashboard/return?action=details&id=<%=loan.getLoanId()%>">
                                <button class="btn btn-primary btn-sm">Aprobar</button>
                            </a>
                            <a style="width: min-content"
                               class="<%=!loan.getLoanStateName().equals("Pendiente de aprobación") ? "" : "d-none"%> <%=loan.getLoanStateName().equals("Finalizado") ? "d-none" : ""%>"
                               href="/dashboard/return?action=finish&id=<%=loan.getLoanId()%>">
                                <button class="btn btn-primary btn-sm">Finalizar</button>
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            initDataTable("material-table")
        });
    </script>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>