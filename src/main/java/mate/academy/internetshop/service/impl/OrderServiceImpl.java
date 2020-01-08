package mate.academy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setItems(items);
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {

        List<Order> userOrders = new ArrayList<>();
        for (Order order : Storage.orders) {
            if (order.getId().equals(user.getId())) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
}
