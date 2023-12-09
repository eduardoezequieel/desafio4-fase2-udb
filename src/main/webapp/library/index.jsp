<%@ page import="models.Inventory" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Librería</title>
    <%@ include file="/components/head.jsp" %>
</head>
<body>
<div class="p-5">
    <h1 class="text-center">Materiales disponibles</h1>
    <div class="table-container">
        <table class="table table-striped" style="width: 100%" id="material-table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Código</th>
                <th scope="col">Título</th>
                <th scope="col">Tipo de material</th>
                <th scope="col">Librería</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Inventory> inventories = (List<Inventory>) request.getAttribute("inventoryList");
                for (Inventory inventory : inventories) {
            %>
            <tr id="row-<%=inventory.getInventoryId()%>">
                <th scope="row"><%= inventory.getInventoryId() %>
                </th>
                <td><%= inventory.getMaterialCode() %>
                </td>
                <td><%= inventory.getTitle() %>
                </td>
                <td><%= inventory.getMaterialTypeName() %>
                </td>
                <td><%= inventory.getLibraryName() %>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <button class="btn btn-primary" onclick="handleClick()">Realizar prestamos</button>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/select/1.7.0/js/dataTables.select.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        $(`#material-table`).DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Spanish.json"
            },
        });
    });

    function getCookie(cname) {
        let name = cname + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    const handleClick = () => {
        const user = getCookie("user_library");

        if (!user) window.location.href = "/login";
        else window.location.href = "/dashboard";
    }
</script>
</body>
</html>
