package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.Item;
import beershop.model.User;
import beershop.service.ItemService;
import beershop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class InjectDataController extends HttpServlet {

    private static final Logger lOGGER = Logger.getLogger(InjectDataController.class);

    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        User user = new User();
        user.setName("Ned");
        user.setSurname("Flanders");
        user.setLogin("USER");
        user.setPassword("1");
        try {
            userService.create(user);
        } catch (DataProcessingException e) {
            lOGGER.error("Can't create user", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        User admin = new User();
        admin.setName("Homer");
        admin.setSurname("Simpson");
        admin.setLogin("ADMIN");
        admin.setPassword("1");
        try {
            userService.create(admin);
        } catch (DataProcessingException e) {
            lOGGER.error("Can't create admin", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        Item ligthBeer = new Item();
        ligthBeer.setName("Podilske svitle");
        ligthBeer.setPrice(10);
        try {
            itemService.create(ligthBeer);
        } catch (DataProcessingException e) {
            lOGGER.error("Can't create Item", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        Item darkBeer = new Item();
        darkBeer.setName("Podilske temne");
        darkBeer.setPrice(12);
        try {
            itemService.create(darkBeer);
        } catch (DataProcessingException e) {
            lOGGER.error("Can't create Item", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
