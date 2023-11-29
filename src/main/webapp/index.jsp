<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar sesión</title>
    <link rel="stylesheet" href="resources/login.css">

</head>
<body>
    <div class="container">
        <h5>Iniciar sesión</h5>
        <div class="inner-container">
            <div class="form-item">
                <label>Usuario</label>
                <input type="text" name="usuario" placeholder="Escribe tu nombre de usuario">
            </div>
            <div class="form-item">
                <label>Contraseña</label>
                <input type="password" name="password" placeholder="Escribe tu contraseña">
                <button id="toggle-password">Toggle contraseña</button>
            </div>
        </div>
        <button class="submit-button" type="button">Continuar</button>
    </div>

    <script src="resources/login.js" type="text/javascript"></script>
</body>
</html>