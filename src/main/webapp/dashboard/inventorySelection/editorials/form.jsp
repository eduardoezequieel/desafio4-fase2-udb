<%@ page import="java.util.List" %>
<%@ page import="models.UserType" %>
<%@ page import="models.User" %>
<%@ page import="models.Editorial" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Editorial</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="editorial/editorial.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container" style="justify-content: center; align-items: center; display: flex;">
            <form action="/dashboard/inventorySelection/editorials" method="post"
                  style="width: 500px">
                <%
                    Editorial editorial = (Editorial) request.getAttribute("editorial");
                %>
                <h2><%= request.getAttribute("title")%>
                </h2>
                <input type="hidden" name="mode" value="<%= request.getAttribute("mode")%>">
                <div class="mb-3">
                    <input type="hidden" name="editorialId"
                           value="<%=editorial != null ? editorial.getEditorialId(): ""%>">
                    <label for="txtName" class="form-label">Nombre</label>
                    <input type="text" name="name" class="form-control" id="txtName" required
                           value="<%=editorial != null ? editorial.getEditorialName(): ""%>">
                </div>
                <button class="btn btn-primary"> Guardar cambios</button>
            </form>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>


