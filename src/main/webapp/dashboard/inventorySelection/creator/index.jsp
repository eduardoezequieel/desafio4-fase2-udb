<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="models.Creator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Creadores</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="creator/creator.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <h1 class="text-center">Creadores</h1>
            <a href="/dashboard/inventorySelection/creator?action=form">
                <button class="primary-button" style="width: min-content; align-self: flex-end">Agregar</button>
            </a>
            <div class="table-container">
                <table class="table table-striped" style="width: 100%" id="creator-table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre del creador</th>
                        <th scope="col">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Creator> creators = (List<Creator>) request.getAttribute("creators");

                        for (Creator creator : creators) {
                    %>
                    <tr>
                        <td><%=creator.getCreatorId()%>
                        </td>
                        <td><%=creator.getCreatorName()%>
                        </td>
                        <td>
                            <a href="/dashboard/inventorySelection/creator?action=edit&id=<%=creator.getCreatorId()%>">
                                <button class="btn btn-primary">Editar</button>
                            </a>
                            <a href="/dashboard/inventorySelection/creator?action=delete&id=<%=creator.getCreatorId()%>">
                                <button class="btn btn-primary">Eliminar</button>
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
            initDataTable("creator-table")
        });
    </script>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>

