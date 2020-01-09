package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    @Override
    public Order update(Order order) {
        Order updatedOrder = get(order.getId())
                .orElseThrow(() -> new NoSuchElementException("Can not find order for update"));
        updatedOrder.setUser(order.getUser());
        updatedOrder.setItems(order.getItems());
        return updatedOrder;
    }

    @Override
    public void delete(Long id) {
        Storage.orders.remove(Storage.orders
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst());
    }

    @Override
    public void delete(Order order) {
        Storage.orders.remove(order);
    }
}
