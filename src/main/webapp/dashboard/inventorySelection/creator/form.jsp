<%@ page import="java.util.List" %>
<%@ page import="models.UserType" %>
<%@ page import="models.User" %>
<%@ page import="models.Creator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Creador</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="creator/creator.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container" style="display:flex; justify-content: center; align-items: center">
            <form action="/dashboard/inventorySelection/creator" method="post" style="width: 500px">
                <%
                    Creator creator = (Creator) request.getAttribute("creator");
                %>
                <h2><%=request.getAttribute("title")%>
                </h2>
                <input type="hidden" name="mode" value="<%= request
                .getAttribute("mode")%>">
                <div class="mb-3">
                    <input type="hidden" name="creatorId" value="<%=creator != null ? creator.getCreatorId() : ""%>">
                    <label for="txtName" class="form-label">Nombre</label>
                    <input type="text" name="name" class="form-control" id="txtName" required
                           value="<%=creator != null ? creator.getCreatorName() : ""%>">
                </div>
                <button class="btn btn-primary">Guardar cambios</button>
            </form>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>

