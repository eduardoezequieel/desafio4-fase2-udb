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
    <div class="page-container">
        <div class="inner-container1">
            <div class="data-container1">
                <form class="form-container">
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
                        <input type="text" class="form-control" id="formGroupExampleInput" placeholder="">
                    </div>
                    <div class="btnUpdate">
                        <button class="primary-button" type="button">Actualizar</button>
                    </div>

                </form>

                <div>
                    <div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">User Type</th>
                                <th scope="col">Allowed to Borrow Materials</th>
                            </tr>
                            </thead>
                            <tbody>

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
