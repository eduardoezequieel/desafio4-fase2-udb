<%@ page import="models.MaterialType" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Creator" %>
<%@ page import="models.Editorial" %>
<%@ page import="models.Material" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Inventario - Material base</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="baseMaterial/baseMaterial.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container" style="justify-content: center; align-items: center">
            <form action="/dashboard/inventorySelection/baseMaterial" method="post">
                <%
                    Material material = (Material) request.getAttribute("material");
                %>
                <input type="hidden" name="code" value="<%=material != null ? material.getCode() : ""%>">
                <h2 class="text-center mb-2"><%=request.getAttribute("title")%></h2>
                <input type="hidden" name="mode" value="<%=request.getAttribute("mode")%>">
                <div class="form-container">
                    <div class="mb-3">
                        <label for="txtTitle" class="form-label">Título</label>
                        <input type="text" name="title" class="form-control" id="txtTitle"
                               value="<%= material != null ? material.getTitle() : "" %>"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="cbMaterialType" class="form-label">Tipo de material</label>
                        <select class="form-select" name="materialType" id="cbMaterialType" <%=request.getAttribute("mode").equals("edit") ? "disabled" : ""%>>
                            <option value="">Selecciona un tipo de material</option>
                            <%
                                List<MaterialType> materialTypes = (List<MaterialType>) request.getAttribute("materialTypes");

                                int selectedMaterialTypeId = material != null ? material.getMaterialTypeId() : -1;

                                for (MaterialType materialType : materialTypes) {
                                    boolean isSelected = materialType.getMaterialTypeId() == selectedMaterialTypeId;
                            %>
                            <option value="<%= materialType.getMaterialTypeId() %>" <%= isSelected ? "selected" : "" %> ><%= materialType.getMaterialTypeName() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="txtDescription" class="form-label">Descripción</label>
                        <textarea name="description" rows="5" class="form-control" id="txtDescription" required><%=material != null ? material.getDescription().trim() : ""%></textarea>
                    </div>
                    <div id="subform">
                        <div class="mb-3" id="author-container">
                            <label for="cbAuthor" class="form-label">Autor</label>
                            <select class="form-select" name="author" id="cbAuthor"
                                    aria-label="Default select example">
                                <option value="">Selecciona un autor</option>
                                <%
                                    List<Creator> authors = (List<Creator>) request.getAttribute("creators");
                                    int selectedAuthorId = material != null ? material.getCreatorId() : -1;

                                    for (Creator author : authors) {
                                        boolean isSelected = author.getCreatorId() == selectedAuthorId;
                                %>
                                <option value="<%= author.getCreatorId() %>" <%=isSelected ? "selected" : ""%> ><%= author.getCreatorName()%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="mb-3" id="editorial-container">
                            <label for="cbEditorial" class="form-label">Editorial</label>
                            <select class="form-select" name="editorial" id="cbEditorial"
                                    aria-label="Default select example">
                                <option value="">Selecciona una editorial</option>
                                <%
                                    List<Editorial> editorials = (List<Editorial>) request.getAttribute("editorials");

                                    int selectedEditorialId = material != null ? material.getEditorialId() : -1;
                                    for (Editorial editorial : editorials) {
                                        boolean isSelected = editorial.getEditorialId() == selectedEditorialId;
                                %>
                                <option value="<%= editorial.getEditorialId() %>" <%=isSelected ? "selected" : ""%>><%= editorial.getEditorialName()%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="mb-3" id="date-container">
                            <label for="txtDate" class="form-label">Fecha de publicación</label>
                            <input type="date" name="date" class="form-control" id="txtDate" value="<%=material != null ? material.getPublishDate() : ""%>">
                        </div>
                        <div class="mb-3" id="duration-container">
                            <label for="txtDuration" class="form-label">Duración</label>
                            <input type="text" name="duration" class="form-control" id="txtDuration" value="<%=material != null ? material.getDuration() : ""%>">
                        </div>
                        <div class="mb-3" id="song-number-container">
                            <label for="txtSongNumber" class="form-label">Número de canciones</label>
                            <input type="text" name="songNumber" class="form-control" id="txtSongNumber" value="<%=material != null ? material.getSongNumber() : ""%>">
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary mt-4">Guardar cambios</button>
                <a href="/dashboard/inventorySelection/baseMaterial?action=index">
                    <button type="button" class="btn btn-secondary mt-4">Regresar</button>
                </a>
            </form>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
    <script type="text/javascript" src="baseMaterial/baseMaterial.js"></script>
</main>
</body>
</html>

