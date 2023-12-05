<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Usuarios</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="users/users.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <h1 class="text-center">Usuarios</h1>
            <a href="/dashboard/users?action=create">
                <button class="primary-button" style="width: min-content; align-self: flex-end">Agregar</button>
            </a>
            <div class="table-container">
                <table class="table table-striped" style="width: 100%" id="user-table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Correo electrónico</th>
                        <th scope="col">Tipo de usuario</th>
                        <th scope="col">Teléfono</th>
                        <th scope="col">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<User> users = (List<User>) request.getAttribute("users");
                        for (User user : users) {
                    %>
                    <tr>
                        <th scope="row"><%= user.getIdentificationCode() %>
                        </th>
                        <td><%= user.getName() %>
                        </td>
                        <td><%= user.getEmail() %>
                        </td>
                        <td><%= user.getUserTypeName() %>
                        </td>
                        <td><%= user.getPhone() %>
                        </td>
                        <td>
                            <a href="/dashboard/users?action=edit&code=<%=user.getIdentificationCode()%>">
                                <button class="btn btn-primary btn-sm">Editar</button>
                            </a>
                            <a href="/dashboard/users?action=delete&code=<%=user.getIdentificationCode()%>">
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
            initDataTable("user-table")
        });
    </script>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>

