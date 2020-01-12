package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;

public class BucketController extends HttpServlet {

    private static final Long USER_ID = 1L;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String itemId = req.getParameter("item_id");

        Bucket bucket = bucketService.getByUser(USER_ID);
        bucketService.addItem(bucket.getId(), Long.valueOf(itemId));

        List<Item> items = bucketService.getAllItems(bucket);

        req.setAttribute("items", items);
        req.setAttribute("bucket", bucket);

        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
