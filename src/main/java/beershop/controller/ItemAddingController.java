package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.Item;
import beershop.service.ItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ItemAddingController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    private static final Logger LOGGER = Logger.getLogger(ItemAddingController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/itemAdding.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Item newItem = new Item();
        newItem.setName(req.getParameter("item_name"));
        newItem.setPrice(Integer.valueOf(req.getParameter("item_price")));
        try {
            itemService.create(newItem);
        } catch (DataProcessingException e) {
            LOGGER.error("Can not create item", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
