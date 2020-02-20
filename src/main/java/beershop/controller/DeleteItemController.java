package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.service.ItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DeleteItemController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    private static final Logger LOGGER = Logger.getLogger(DeleteItemController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String itemId = req.getParameter("item_id");
        try {
            itemService.delete(Long.parseLong(itemId));
        } catch (DataProcessingException e) {
            LOGGER.error("Can not delete item", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
