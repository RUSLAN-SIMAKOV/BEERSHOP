package beershop.controller;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.Bucket;
import beershop.model.Item;
import beershop.model.Order;
import beershop.service.BucketService;
import beershop.service.OrderService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ShowItemsInOrderController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    private static final Logger LOGGER = Logger.getLogger(ShowItemsInOrderController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Order order = new Order();
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        List<Item> items = new ArrayList<>();
        try {
            Bucket bucket = bucketService.getByUser(userId);
            items = bucketService.getAllItems(bucket);
            order.setItems(items);
            order.setUserId(userId);
            orderService.create(order);
        } catch (DataProcessingException e) {
            LOGGER.error("Can not create order", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }

        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }
}
