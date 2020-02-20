package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.service.BucketService;
import beershop.service.ItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DeleteItemFromBucketController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    private static final Logger LOGGER = Logger.getLogger(DeleteItemFromBucketController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession(true).getAttribute("userId");
        String itemId = req.getParameter("item_id");
        try {
            bucketService.deleteItem(bucketService.getByUser(userId),
                    itemService.get(Long.valueOf(itemId)));
        } catch (DataProcessingException e) {
            LOGGER.error("Can not delete item from bucket", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/showBucket");
    }
}
