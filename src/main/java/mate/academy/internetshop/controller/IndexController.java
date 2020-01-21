package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

public class IndexController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /******************************************************************
         * ЭТО ВРЕМЕННО
         ************************************************************/
        Item item = new Item();
        item.setName("Max");
        item.setPrice(777);
        item.setId(7L);
        itemService.create(item);
        itemService.get(1L);
        itemService.delete(17L);
        itemService.update(item);

        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
