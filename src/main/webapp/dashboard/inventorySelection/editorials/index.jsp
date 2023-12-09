<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="models.Editorial" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Editorial</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="users/users.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <h1 class="text-center">Editoriales</h1>
            <a href="/dashboard/inventorySelection/editorials?action=form">
                <button class="primary-button" style="width: min-content; align-self: flex-end">Agregar</button>
            </a>
            <div class="table-container">
                <table class="table table-striped" style="width: 100%" id="editorial-table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre de editoriales</th>
                        <th scope="col">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Editorial> editorials = (List<Editorial>) request.getAttribute("editorials");
                        for (Editorial editorial : editorials) {
                    %>
                    <tr>
                        <td><%=editorial.getEditorialId() %>
                        </td>
                        <td><%=editorial.getEditorialName() %>
                        </td>
                        <td>
                            <a href="/dashboard/inventorySelection/editorials?action=edit&id=<%= editorial.getEditorialId()%>">
                                <button class="btn btn-primary"> Editar</button>
                            </a>
                            <a href="/dashboard/inventorySelection/editorials?action=delete&id=<%= editorial.getEditorialId()%>">
                                <button class="btn btn-primary"> Eliminar</button>
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
            initDataTable("editorial-table")
        });
    </script>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>

