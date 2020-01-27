package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.Order;

public interface OrderDao {

    Order create(Order order, Long userId);

    Optional<Order> get(Long id);

    Order update(Order order);

    void delete(Long id);

    void delete(Order order);
}
