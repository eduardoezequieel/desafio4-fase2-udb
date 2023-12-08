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
                <form class="form-container" style="margin: 15px">
                    <div>
                        <label for="formGroupExampleInput" class="form-label">Tipo de Usuario</label>
                        <select class="form-select form-select-lg mb-3" aria-label="Large select example">
                            <option selected>Seleccione un tipo de Usuario</option>
                            <option value="1">Seleccione un tipo de Usuario</option>
                            <option value="2">Profesor</option>
                            <option value="3">Estudiante</option>
                        </select>
                    </div>
                    <div>
                        <label for="formGroupExampleInput" class="form-label">Cantidad de Libros que puede
                            prestar</label>
                        <input type="text" class="form-control" id="formGroupExampleInput" placeholder=""
                               style="margin: 20px 0">
                    </div>
                    <div class="btnUpdate" style="width: 50%">
                        <button class="primary-button" type="button">Actualizar</button>
                    </div>

                </form>

                <div>
                    <div>
                        <table class="table" id="usertype-table">
                            <thead>
                            <tr>
                                <th scope="col">Tipo de usuario</th>
                                <th scope="col">Cantidad de Materiales que puede prestar</th>

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