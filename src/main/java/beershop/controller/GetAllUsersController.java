package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.User;
import beershop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class GetAllUsersController extends HttpServlet {

    @Inject
    private static UserService userService;

    private static final Logger LOGGER = Logger.getLogger(GetAllUsersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<User> users = null;
        try {
            users = userService.getAll();
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("greeting", "Welcome!");
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/getAllUsers.jsp").forward(req, resp);
    }
}
