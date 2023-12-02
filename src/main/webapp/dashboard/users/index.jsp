<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard | Usuarios</title>
    <%@ include file="/components/head.jsp" %>
    <link rel="stylesheet" href="users.css">
</head>
<body>
<main>cv
    <%@ include file="/components/sidebar.jsp" %>
    <div class="page-container">
        <div class="inner-container">
            <div class="data-container">
                <form class="form-container">
                    <div class="mb-3">
                        <label for="txtCode" class="form-label">Código de identificación</label>
                        <input type="text" class="form-control" id="txtCode">
                    </div>
                    <div class="mb-3">
                        <label for="txtName" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="txtName">
                    </div>
                    <div class="mb-3">
                        <label for="txtEmail" class="form-label">Correo electrónico</label>
                        <input type="email" class="form-control" id="txtEmail">
                    </div>
                    <div class="mb-3">
                        <label for="txtPhone" class="form-label">Teléfono</label>
                        <input type="text" class="form-control" id="txtPhone">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tipo de usuario</label>
                        <select class="form-select">
                            <option selected>Selecciona un tipo de usuario</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="txtPassword" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="txtPassword">
                    </div>
                    <button type="submit" class="btn btn-primary d-none">Submit</button>
                </form>
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
            <div class="button-group">
                <button class="primary-button" type="button">Agregar</button>
                <button class="primary-button" type="button">Actualizar</button>
                <button class="primary-button" type="button">Eliminar</button>
                <button class="primary-button" type="button" id="restore-password">Reestablecer contraseña</button>
                <button class="primary-button" type="button">Limpiar</button>
            </div>
        </div>
    </div>
    <%@ include file="/components/scripts.jsp" %>
</main>
</body>
</html>

