package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;

    private static Logger logger = Logger.getLogger(RegistrationController.class);

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
            logger.error(e);
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
