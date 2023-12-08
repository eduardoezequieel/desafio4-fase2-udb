<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Settings</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="settingsSelection.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <div class="grid-container">
                <a href="/dashboard/settingsSelection/allowedBorrowedMaterials?action=index">
                    <button type="button" class="selection-button">Configurar Cantidad de Prestamos</button>
                </a>
                <a href="/dashboard/settingsSelection/yearlyOverdue?action=index">
                    <button type="button" class="selection-button">Configurar Mora Anual</button>
                </a>
            </div>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>
