<%@ page import="models.Loan" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Material" %>
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
        <div class="inner-container d-flex justify-content-center align-items-center flex-column" style="gap: 10px">
            <h1><%=request.getAttribute("title")%>
            </h1>
            <div style="width: 500px; gap: 10px" class="d-flex flex-column">
                <%
                    List<Material> materials = (List<Material>) request.getAttribute("materials");

                    for (Material material : materials) {
                %>
                <div class="d-flex flex-column border rounded p-3">
                    <p class="p-0 m-0"><b>ID: </b><%=material.getInventoryId()%>
                    </p>
                    <p class="p-0 m-0"><b>Título: </b><%=material.getTitle()%>
                    </p>
                    <p class="p-0 m-0"><b>Tipo de material: </b><%=material.getMaterialTypeName()%>
                    </p>
                    <p class="p-0 m-0"><b>Ubicación: </b><%=material.getLibraryName()%>
                </div>
                <%}%>
            </div>
            <a href="/dashboard/return?action=index" style="width: min-content">
                <button type="button" class="btn btn-primary">Regresar</button>
            </a>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>