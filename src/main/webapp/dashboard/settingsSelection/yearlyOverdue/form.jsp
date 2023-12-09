<%@ page import="java.util.List" %>
<%@ page import="models.UserType" %>
<%@ page import="models.User" %>
<%@ page import="models.Editorial" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Cantidad de Prestamos</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="editorial/editorial.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container" style="justify-content: center; align-items: center; display: flex;">
            <form action="/dashboard/settingsSelection/yearlyOverdue" method="post"
                  style="width: 500px">
                <h2><%= request.getAttribute("title")%>
                </h2>
                <input type="hidden" name="mode" value="<%= request.getAttribute("mode")%>">
                <div class="mb-3">
                    <input type="hidden" name="year"
                           value="<%=request.getParameter("year")%>">
                    <label for="txtName" class="form-label">Costo Mora Anual</label>
                    <input type="text" name="cost" class="form-control" id="txtName" required
                           value="<%= request.getParameter("cost")%>">
                </div>
                <button class="btn btn-primary"> Guardar cambios</button>
            </form>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>
