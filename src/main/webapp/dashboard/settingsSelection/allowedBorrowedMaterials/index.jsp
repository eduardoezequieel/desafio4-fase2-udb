<%@ page import="models.UserType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Settings</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="allowedBorrowedMaterials.css">
</head>
<body>
<main>
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container"
         style="display: flex; flex-direction: column; align-items: center;  justify-content: center; height: 100vh;">
        <div class="inner-container1">
            <div class="data-container1" style="display: flex; center: space-between;">
                <div>
                    <div>
                        <table class="table" id="usertype-table">
                            <thead>
                            <tr>
                                <th scope="col">Tipo de usuario</th>
                                <th scope="col">Cantidad de Materiales que puede prestar</th>
                                <th scope="col">Acciones</th>

                            </tr>
                            </thead>
                            <tbody>
                            <%
                                List<UserType> userTypesNamesList = (List<UserType>) request.getAttribute("twoUserTypes");
                                for (UserType userType : userTypesNamesList) {
                            %>
                            <tr id="row-<%=userType.getUserTypeName()%>">
                                <th scope="row"><%= userType.getUserTypeName() %>
                                </th>
                                <td><%= userType.getAllowedBorrowedMaterials() %>
                                </td>
                                <td>
                                    <a href="/dashboard/settingsSelection/allowedBorrowedMaterials?action=edit&userTypeName=<%=userType.getUserTypeName()%>&allowedBorrowedMaterials=<%=userType.getAllowedBorrowedMaterials()%>">
                                        <button class="primary-button" type="button">Actualizar</button>
                                    </a>

                                </td>
                            </tr>
                            <%
                                }
                            %>

                            </tbody>
                        </table>
                    </div>

                </div>

            </div>

        </div>


    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>