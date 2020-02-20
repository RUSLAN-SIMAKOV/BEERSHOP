package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.User;
import beershop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User newUser = new User();
        newUser.setLogin(req.getParameter("user_login"));
        newUser.setPassword(req.getParameter("user_password"));
        newUser.setSurname(req.getParameter("user_surname"));
        newUser.setName(req.getParameter("user_name"));
        User user = null;
        try {
            user = userService.create(newUser);
        } catch (DataProcessingException e) {
            LOGGER.error("Can not create new user", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.getId());
        String token = user.getToken();
        user.setToken(token);

        Cookie cookie = new Cookie("BEERSHOP", token);
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllUsers");
    }
}
