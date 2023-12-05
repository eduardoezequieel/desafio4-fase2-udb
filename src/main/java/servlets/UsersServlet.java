package servlets;

import controllers.UsersCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import models.UserType;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/dashboard/users")
public class UsersServlet extends HttpServlet {
    UsersCtrl controller = new UsersCtrl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<UserType> userTypes = controller.getUserTypes();
        List<User> users = controller.getUsers();

        for (User user : users) {
            System.out.println(user.getEmail());
        }

        req.setAttribute("userTypes", userTypes);
        req.setAttribute("users", users);

        switch (action) {
            case "index":
                req.getRequestDispatcher("/dashboard/users/index.jsp").forward(req, resp);
                break;
            case "delete":
                String codeToDelete = req.getParameter("code");

                boolean ok = controller.deleteUser(codeToDelete);

                if (ok) {
                    req.setAttribute("message", "Usuario eliminado correctamente");
                    req.setAttribute("messageType", "success");
                    req.setAttribute("href", "/dashboard/users?action=index");
                } else {
                    req.setAttribute("message", "Error al eliminar usuario");
                    req.setAttribute("messageType", "error");
                    req.setAttribute("href", "/dashboard/users?action=index");
                }

                req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                break;
            case "create":
                req.setAttribute("mode", "add");
                req.setAttribute("title", "Crear usuario");
                req.getRequestDispatcher("/dashboard/users/form.jsp").forward(req, resp);
                break;
            case "edit":
                req.setAttribute("mode", "edit");
                req.setAttribute("title", "Editar usuario");
                String code = req.getParameter("code");

                User foundUser = null;

                for (User user : users) {
                    if (user.getIdentificationCode().equals(code)) {
                        foundUser = user;
                        break;
                    }
                }

                if (foundUser != null) {
                    req.setAttribute("user", foundUser);
                } else {
                    req.setAttribute("message", "Usuario no encontrado");
                    req.setAttribute("messageType", "error");
                    req.setAttribute("href", "/dashboard/users?action=index");
                    req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
                    return;
                }

                req.getRequestDispatcher("/dashboard/users/form.jsp").forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name"),
                email = req.getParameter("email"),
                password = req.getParameter("password"),
                identificationCode = req.getParameter("userId"),
                phone = req.getParameter("phone");

        int userType = Integer.parseInt(req.getParameter("userType"));

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setIdentificationCode(identificationCode);
        user.setUserTypeId(userType);
        user.setPhone(phone);

        String mode = req.getParameter("mode");

        if (mode.equals("add")) {
            boolean ok = controller.registerUser(user);

            if (ok) {
                req.setAttribute("message", "Usuario registrado correctamente");
                req.setAttribute("messageType", "success");
                req.setAttribute("href", "/dashboard/users?action=index");
            } else {
                req.setAttribute("message", "Error al registrar usuario");
                req.setAttribute("messageType", "error");
                req.setAttribute("href", "/dashboard/users?action=index");
            }

            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
        } else {
            boolean ok = controller.updateUser(user, user.getIdentificationCode());

            if (ok) {
                req.setAttribute("message", "Usuario actualizado correctamente");
                req.setAttribute("messageType", "success");
                req.setAttribute("href", "/dashboard/users?action=index");
            } else {
                req.setAttribute("message", "Error al actualizar usuario");
                req.setAttribute("messageType", "error");
                req.setAttribute("href", "/dashboard/users?action=index");
            }

            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
        }


    }
}
