package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.AbstractDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {

    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Inject
    private static ItemService itemService;

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order, Long userId) {

        String query = "INSERT INTO beershop.orders (id_user) "
                + "VALUES (?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                long bucketId = resultSet.getLong(1);
                order.setId(bucketId);
            }
        } catch (SQLException e) {
            logger.error("Can not create order! ", e);
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {

        Order order = new Order();
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM beershop.orders "
                + "INNER JOIN beershop.orders_items ON orders.id_order = orders_items.id_order "
                + "where id_order=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id_order");
                long userId = resultSet.getLong("id_user");
                long itemId = resultSet.getLong("id_item");
                order.setId(id);
                order.setUserId(userId);
                items.add(itemService.get(itemId));
            }
            order.setItems(items);
        } catch (SQLException e) {
            logger.error("Can not get bucket! ", e);
        }
        return Optional.of(order);

    }

    @Override
    public Order update(Order order) {

        Item updatedItem = null;
        String query = "INSERT INTO beershop.orders (id_order, id_item) "
                + "VALUES (?, ?);";
        try (
                PreparedStatement statement
                        = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            for (Item item : order.getItems()) {
                updatedItem = item;
            }
            statement.setLong(2, updatedItem.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update order! ", e);
        }
        return order;
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM beershop.orders where id_order=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete order with id " + id, e);
        }
        String queryDeleteUser = "DELETE FROM beershop.orders_items where id_order=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteUser)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete order with id " + id, e);
        }
    }

    @Override
    public void delete(Order order) {

        String query = "DELETE FROM beershop.orders where id_order=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete order with id " + order.getId(), e);
        }
        String queryDeleteUser = "DELETE FROM beershop.orders_items where id_order=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteUser)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete order with id " + order.getId(), e);
        }
    }
}
