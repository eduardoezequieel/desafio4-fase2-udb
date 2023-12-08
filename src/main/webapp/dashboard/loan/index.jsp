<%@ page import="java.util.List" %>
<%@ page import="models.Inventory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Prestamos</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="loan/loan.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <h1 class="text-center">Selecciona los materiales para el prestamo</h1>
            <div class="d-flex">
                <div class="table-container">
                    <table class="table table-striped" style="width: 100%" id="material-table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Código</th>
                            <th scope="col">Título</th>
                            <th scope="col">Tipo de material</th>
                            <th scope="col">Librería</th>
                            <th scope="col">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Inventory> inventories = (List<Inventory>) request.getAttribute("inventoryList");
                            for (Inventory inventory : inventories) {
                        %>
                        <tr id="row-<%=inventory.getInventoryId()%>">
                            <th scope="row"><%= inventory.getInventoryId() %>
                            </th>
                            <td><%= inventory.getMaterialCode() %></td>
                            <td><%= inventory.getTitle() %></td>
                            <td><%= inventory.getMaterialTypeName() %></td>
                            <td><%= inventory.getLibraryName() %></td>
                            <td>
                                <button inventory-id="<%=inventory.getInventoryId()%>" class="table-item btn btn-primary btn-sm d-flex justify-content-center align-items-center">Agregar</button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <form action="/dashboard/loan" class="px-3" method="post">
                    <h5 class="text-center">Detalle del prestamo</h5>
                    <div class="mb-3">
                        <label for="startDate">Fecha de inicio</label>
                        <input required type="date" class="form-control" name="startDate" id="startDate">
                    </div>
                    <div class="mb-3">
                        <label for="endDate">Fecha de finalización</label>
                        <input required type="date" class="form-control" name="endDate" id="endDate">
                    </div>
                    <input type="text" hidden name="selectedMaterials" id="selected-materials">
                    <div id="selected-materials-container" class="selected-materials d-flex flex-column w-100"></div>
                    <button type="submit" class="btn btn-primary mt-3">Solicitar prestamo</button>
                </form>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            initDataTable("material-table")
        });
    </script>
    <script type="text/javascript" src="loan/loan.js"></script>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>

