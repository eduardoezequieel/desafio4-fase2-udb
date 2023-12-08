<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar sesión</title>
    <link rel="stylesheet" href="/login/login.css">
    <link rel="stylesheet" href="../globals.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
</head>
<body>
    <div class="container">
        <h5>Iniciar sesión</h5>
        <div class="inner-container">
            <form action="/login" method="post">
                <div class="form-item">
                    <label>Correo electrónico</label>
                    <input required type="email" name="email" placeholder="Escribe tu nombre de usuario">
                </div>
                <div class="form-item">
                    <label>Contraseña</label>
                    <div class="password-container">
                        <input required type="password" name="password" placeholder="Escribe tu contraseña">
                        <button id="toggle-password"><i class="bi bi-eye-fill"></i></button>
                    </div>
                </div>
                <button type="submit" class="submit-button">Continuar</button>
            </form>
        </div>
        
    </div>

    <script src="/login/login.js" type="text/javascript"></script>
    <script src="/globals.js" type="text/javascript"></script>
</body>
</html>