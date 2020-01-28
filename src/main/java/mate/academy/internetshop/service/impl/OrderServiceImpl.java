package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.exception.DataProcessingException;
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
    public Order create(Order order) throws DataProcessingException {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) throws DataProcessingException {
        return orderDao.get(id).get();
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) throws DataProcessingException {
        orderDao.delete(id);
    }

    @Override
    public void delete(Order order) throws DataProcessingException {
        orderDao.delete(order);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) throws DataProcessingException {
        Order order = new Order();
        order.setUserId(user.getId());
        order.setItems(items);
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {

        return Storage.orders.stream()
                .filter(o -> o.getId().equals(user.getId()))
                .collect(Collectors.toList());
    }
}
