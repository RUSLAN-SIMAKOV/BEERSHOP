package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class InjectDataController extends HttpServlet {

    private static Logger logger = Logger.getLogger(InjectDataController.class);

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        User user = new User();
        user.setName("Ned");
        user.setSurname("Flanders");
        user.setLogin("red");
        user.setPassword("2");
        try {
            userService.create(user);
        } catch (DataProcessingException e) {
            logger.error(e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        User admin = new User();
        admin.setName("Homer");
        admin.setSurname("Simpson");
        admin.setLogin("blue");
        admin.setPassword("1");
        try {
            userService.create(admin);
        } catch (DataProcessingException e) {
            logger.error(e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        Item ligthBeer = new Item();
        ligthBeer.setName("Podilske svitle");
        ligthBeer.setPrice(10);

        Item darkBeer = new Item();
        darkBeer.setName("Podilske temne");
        darkBeer.setPrice(12);

        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
