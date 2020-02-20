package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.Item;
import beershop.service.ItemService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class GetAllItemsController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GetAllItemsController.class);

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Item> items = new ArrayList();
        try {
            items.addAll(itemService.getAllItems());
        } catch (DataProcessingException e) {
            LOGGER.error("Can not get all items", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/purchasing.jsp").forward(req, resp);
    }
}
