package beershop.dao.jdbc;

import beershop.dao.AbstractDao;
import beershop.dao.OrderDao;
import beershop.exception.DataProcessingException;
import beershop.lib.Dao;
import beershop.lib.Inject;
import beershop.model.Item;
import beershop.model.Order;
import beershop.service.ItemService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {

    @Inject
    private static ItemService itemService;

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {

        String query = "INSERT INTO beershop.orders (id_user) "
                + "VALUES (?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                long bucketId = resultSet.getLong(1);
                order.setId(bucketId);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can not create order! ", e);
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long id) throws DataProcessingException {

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
            throw new DataProcessingException("Can not get bucket! ", e);
        }
        return Optional.of(order);

    }

    @Override
    public Order update(Order order) throws DataProcessingException {

        deleteOldOrder(order.getId());

        for (Item item : order.getItems()) {
            String query = "INSERT INTO beershop.orders (id_order, id_item) "
                    + "VALUES (?, ?);";
            try (
                    PreparedStatement statement
                            = connection.prepareStatement(query)) {
                statement.setLong(1, order.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Can not update order! ", e);
            }
        }
        return order;
    }

    public void deleteOldOrder(Long id) throws DataProcessingException {

        String queryDeleteUser = "DELETE FROM beershop.orders_items where id_order=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteUser)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not delete order with id " + id, e);
        }
    }

    @Override
    public void delete(Long id) throws DataProcessingException {

        deleteOldOrder(id);

        String query = "DELETE FROM beershop.orders where id_order=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not delete order with id " + id, e);
        }
    }

    @Override
    public void delete(Order order) throws DataProcessingException {
        delete(order.getId());
    }
}
