package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.Bucket;
import beershop.model.Item;
import beershop.service.BucketService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AddItemToBucketController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddItemToBucketController.class);

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String itemId = req.getParameter("item_id");

        Long userId = (Long) req.getSession(true).getAttribute("userId");

        Bucket bucket = null;
        try {
            bucket = bucketService.getByUser(userId);
            if (bucket.getId() == null) {
                bucket.setUserId(userId);
                bucketService.create(bucket);
            }
            bucketService.addItem(bucket, Long.valueOf(itemId));
        } catch (DataProcessingException e) {
            LOGGER.error("Can not add item to the bucket", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        List<Item> items = bucketService.getAllItems(bucket);
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
