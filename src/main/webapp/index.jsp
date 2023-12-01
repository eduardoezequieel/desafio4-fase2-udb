<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar sesión</title>
    <link rel="stylesheet" href="/login.css">
    <link rel="stylesheet" href="/globals.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
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
                <div class="password-container">
                    <input type="password" name="password" placeholder="Escribe tu contraseña">
                    <button id="toggle-password"><i class="bi bi-eye-fill"></i></button>
                </div>
            </div>
        </div>
        <a href="dashboard">
            <button class="submit-button" type="button">Continuar</button>
        </a>
    </div>

    <script src="login.js" type="text/javascript"></script>
</body>
</html>