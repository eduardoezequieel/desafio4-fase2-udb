<%@ page import="java.util.List" %>
<%@ page import="models.UserType" %>
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
        <div class="inner-container" style="justify-content: center; align-items: center">
            <form action="/dashboard/users" method="post">
                <%
                    User user = (User) request.getAttribute("user");
                %>
                <input type="hidden" name="mode" value="<%=request.getAttribute("mode")%>">
                <input type="hidden" name="userId" value="<%=user != null ? user.getIdentificationCode() : ""%>">
                <h2 class="text-center mb-2"><%=request.getAttribute("title")%>
                </h2>
                <div class="form-container">
                    <div class="mb-3">
                        <label for="txtIdentification" class="form-label">Código de identificación</label>
                        <input type="text" name="identificationCode" class="form-control" id="txtIdentification"
                            <% if(request.getAttribute("mode").equals("edit")) { %>
                               disabled <% } %>
                               value="<%=user != null ? user.getIdentificationCode() : ""%>"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="txtName" class="form-label">Nombre</label>
                        <input type="text" name="name" class="form-control" id="txtName" required
                               value="<%=user != null ? user.getName() : ""%>">
                    </div>
                    <div class="mb-3">
                        <label for="txtEmail" class="form-label">Correo electrónico</label>
                        <input type="email" name="email" class="form-control" id="txtEmail" required
                               value="<%=user != null ? user.getEmail() : ""%>">
                    </div>
                    <div class="mb-3">
                        <label for="txtPhone" class="form-label">Teléfono</label>
                        <input type="text" name="phone" class="form-control" id="txtPhone" required
                               value="<%=user != null ? user.getPhone() : ""%>">
                    </div>
                    <div class="mb-3">
                        <label for="cbUserType" class="form-label">Tipo de usuario</label>
                        <select class="form-select" name="userType" required id="cbUserType"
                                aria-label="Default select example">
                            <option value="">Selecciona un tipo de usuario</option>
                            <%
                                List<UserType> userTypes = (List<UserType>) request.getAttribute("userTypes");
                                int selectedUserTypeId = user != null ? user.getUserTypeId() : -1;

                                for (UserType userType : userTypes) {
                                    boolean isSelected = userType.getUserTypeId() == selectedUserTypeId;
                            %>
                            <option value="<%= userType.getUserTypeId() %>" <%= isSelected ? "selected" : "" %>><%= userType.getUserTypeName() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>


                    <div class="mb-3">
                        <label for="txtPassword" class="form-label">Contraseña</label>
                        <input type="password" name="password"
                               class="form-control" required <% if(request.getAttribute("mode").equals("edit")) { %>
                               disabled <% } %>
                               id="txtPassword">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mt-4">Guardar cambios</button>
                <a href="/dashboard/users?action=index">
                    <button type="button" class="btn btn-secondary mt-4">Regresar</button>
                </a>
            </form>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>

