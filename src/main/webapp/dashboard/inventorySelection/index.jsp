<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Dashboard | Inventario</title>
  <%@ include file="/components/head.jsp" %>
  <link rel="stylesheet" href="inventorySelection.css">
</head>
<body>
<main>
  <%@ include file="/components/sidebar.jsp" %>
  <div class="page-container">
    <div class="inner-container">
      <div class="grid-container">
        <a href="${pageContext.request.contextPath}/dashboard/inventorySelection/baseMaterial?action=index">
          <button type="button" class="selection-button">Material base</button>
        </a>
        <a href="">
          <button type="button" class="selection-button">Inventario</button>
        </a>
        <a href="">
          <button type="button" class="selection-button">Creadores</button>
        </a>
        <a href="">
          <button type="button" class="selection-button">Editoriales</button>
        </a>
      </div>
    </div>
  </div>
  <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>
