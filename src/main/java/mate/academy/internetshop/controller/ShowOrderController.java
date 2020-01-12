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
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;

public class ShowOrderController extends HttpServlet {

    private static final Long USER_ID = 1L;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Order order = new Order();

        Bucket bucket = bucketService.getByUser(USER_ID);

        orderService.create(order);

        List<Item> items = bucketService.getAllItems(bucket);

        req.setAttribute("items", items);

        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }
}
