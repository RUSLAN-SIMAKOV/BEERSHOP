package mate.academy.internetshop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class LoginController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            User user = userService.login(req.getParameter("user_login"),
                    req.getParameter("user_password"));
            Cookie cookie = new Cookie("BEERSHOP", user.getToken());
            resp.addCookie(cookie);
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } catch (AuthenticationException e) {
            req.setAttribute("error", "Incorrect login or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }

    }
}
