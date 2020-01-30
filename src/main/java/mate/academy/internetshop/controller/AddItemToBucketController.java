package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import org.apache.log4j.Logger;

public class AddItemToBucketController extends HttpServlet {

    private static Logger logger = Logger.getLogger(AddItemToBucketController.class);

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
            logger.error(e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        List<Item> items = bucketService.getAllItems(bucket);
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
