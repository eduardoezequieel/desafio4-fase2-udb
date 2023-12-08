<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside>
    <div id="top-sidebar">
        <a id="logo" href="/dashboard">
            <h3>Libreria</h3>
            <input type="text" class="d-none" id="allowedBorrowedMaterials"/>
        </a>
        <div class="buttons-container">
            <a href="/dashboard/users?action=index" class="d-none">
                <button type="button">
                    <i class="bi bi-person"></i>
                    <span>Usuarios</span>
                </button>
            </a>
            <a href="/dashboard/inventorySelection" class="d-none">
                <button type="button">
                    <i class="bi bi-archive"></i>
                    <span>Inventario</span>
                </button>
            </a>
            <a href="/dashboard/loan?action=index" class="d-none">
                <button type="button">
                    <i class="bi bi-coin"></i>
                    <span>Prestamos</span>
                </button>
            </a>
            <a href="/dashboard/return?action=index" class="d-none">
                <button type="button">
                    <i class="bi bi-person-raised-hand"></i>
                    <span>Devoluciones</span>
                </button>
            </a>
        </div>
    </div>
    <div id="bottom-sidebar">
        <div class="user-info">
            <h4 id="username-lbl"></h4>
            <p id="role-lbl"></p>
        </div>
        <div class="buttons-container">
            <a href="/dashboard/settingsSelection">
                <button type="button">
                    <i class="bi bi-gear"></i>
                    <span>Configuración</span>
                </button>
            </a>
            <button type="button" onclick="logout()">
                <i class="bi bi-box-arrow-left"></i>
                <span>Cerrar sesión</span>
            </button>
        </div>
    </div>
</aside>
