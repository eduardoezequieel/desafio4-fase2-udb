package servlets;

import controllers.UsersCtrl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email"), 
                password = req.getParameter("password");

        UsersCtrl usersCtrl = new UsersCtrl();

        boolean userExists = usersCtrl.checkIfUserExists(email);

        if (!userExists) {
            req.setAttribute("message", "El usuario no existe.");
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/login");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        }

        boolean isLoggedIn = usersCtrl.checkPassword(email, password);

        if (!isLoggedIn) {
            req.setAttribute("message", "Contraseña incorrecta.");
            req.setAttribute("messageType", "error");
            req.setAttribute("href", "/login");
            req.getRequestDispatcher("/components/message.jsp").forward(req, resp);
            return;
        }

        User user = usersCtrl.getLoggedUser(email);
        String cookieValue = URLEncoder.encode(user.toString(), "UTF-8");
        Cookie cookie = new Cookie("user_library", cookieValue);
        
        resp.addCookie(cookie);
        
        resp.sendRedirect("/dashboard?action=index");
    }
}
