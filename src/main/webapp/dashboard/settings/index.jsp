<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Usuarios</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="settings.css">
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
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td colspan="2">Larry the Bird</td>
                            <td>@twitter</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
            <div class="data-container2">
                <form class="form-container">
                    <div>
                        <label for="formGroupExampleInput" class="form-label">Año</label>
                        <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="">
                    </div>
                    <div>
                        <label for="formGroupExampleInput" class="form-label">Tarifa</label>
                        <input type="text" class="form-control" id="formGroupExampleInput3" placeholder="">
                    </div>
                    <div class="btnUpdate">
                        <button class="primary-button" type="button">Actualizar</button>
                    </div>
                </form>


                <div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td colspan="2">Larry the Bird</td>
                            <td>@twitter</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>


    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>
