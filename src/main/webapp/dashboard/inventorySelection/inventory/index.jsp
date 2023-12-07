<%@ page import="java.util.List" %>
<%@ page import="models.Material" %>
<%@ page import="models.Inventory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Inventario</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="inventory.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <h1 class="text-center">Inventario</h1>
            <a href="/dashboard/inventorySelection/inventory?action=create">
                <button class="primary-button" style="width: min-content; align-self: flex-end">Agregar</button>
            </a>
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
                        List<Inventory> inventories = (List<Inventory>) request.getAttribute("inventories");
                        for (Inventory inventory : inventories) {
                    %>
                    <tr>
                        <th scope="row"><%= inventory.getInventoryId() %>
                        </th>
                        <td><%= inventory.getTitle() %>
                        </td>
                        <td><%= inventory.getMaterialCode() %>
                        </td>
                        <td><%= inventory.getMaterialTypeName() %></td>
                        <td><%= inventory.getLibraryName() %></td>
                        <td>
                            <a href="/dashboard/inventorySelection/baseMaterial?action=edit&code=<%=inventory.getInventoryId()%>">
                                <button class="btn btn-primary btn-sm">Editar</button>
                            </a>
                            <a href="/dashboard/inventorySelection/baseMaterial?action=delete&code=<%=inventory.getInventoryId()%>">
                                <button class="btn btn-primary btn-sm">Eliminar</button>
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

