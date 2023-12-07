<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Inventario</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="inventory/inventory.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container" style="display: flex; justify-content: center; align-items: center">
            <form action="/dashboard/inventorySelection/baseMaterial" method="post">
                <%
                    Inventory inventory = (Inventory) request.getAttribute("inventory");
                %>
                <input type="hidden" name="code" value="<%=inventory != null ? inventory.getInventoryId() : ""%>">
                <h2 class="text-center mb-2"><%=request.getAttribute("title")%></h2>
                <input type="hidden" name="mode" value="<%=request.getAttribute("mode")%>">
                <div class="form-container">
                    <div class="mb-3">
                        <label for="cbMaterial" class="form-label">Material</label>
                        <select class="form-select" name="material" id="cbMaterial" <%=request.getAttribute("mode").equals("edit") ? "disabled" : ""%>>
                            <option value="">Selecciona un material</option>
                            <%
                                List<Material> materials = (List<Material>) request.getAttribute("materials");

                                String selectedMaterialCode = inventory != null ? inventory.getMaterialCode() : "";

                                for (Material material : materials) {
                                    boolean isSelected = material.getCode().equals(selectedMaterialCode);
                            %>
                            <option value="<%= material.getCode() %>" <%= isSelected ? "selected" : "" %> material-type="<%=material.getMaterialTypeName()%>"><%= material.getTitle() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="cbMaterial" class="form-label">Librería</label>
                        <select class="form-select" name="library" id="cbLibrary" <%=request.getAttribute("mode").equals("edit") ? "disabled" : ""%>>
                            <option value="">Selecciona una librería</option>
                            <%
                                List<Library> libraries = (List<Library>) request.getAttribute("libraries");

                                int selectedLibraryId = inventory != null ? inventory.getLibraryId() : -1;

                                for (Library library : libraries) {
                                    boolean isSelected = library.getLibraryId() == selectedLibraryId;
                            %>
                            <option value="<%= library.getLibraryId() %>" <%= isSelected ? "selected" : "" %>><%= library.getLibraryName() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="txtNotes" class="form-label">Notas</label>
                        <textarea name="notes" rows="5" class="form-control" id="txtNotes" required><%=inventory != null ? inventory.getNotes().trim() : ""%></textarea>
                    </div>
                    <div>
                        <div class="mb-3">
                            <label for="txtIssn" class="form-label">ISSN</label>
                            <input type="text" class="form-control" id="txtIssn" required name="issn">
                        </div>
                        <div class="mb-3">
                            <label for="txtIsbn" class="form-label">ISBN</label>
                            <input type="text" class="form-control" id="txtIsbn" required name="issn">
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
    <script type="text/javascript" src="inventory/inventory.js"></script>
</main>
</body>
</html>

