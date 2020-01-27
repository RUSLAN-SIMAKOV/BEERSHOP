package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.AbstractDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {

        String query = "INSERT INTO beershop.items (name, price) VALUES (?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not create item! ", e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {

        String query = "SELECT * FROM beershop.items where id=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                Item item = new Item();
                item.setId(itemId);
                item.setName(name);
                item.setPrice(price);

                return Optional.of(item);
            }
        } catch (SQLException e) {
            logger.error("Can not get item with id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {

        String query = "UPDATE beershop.items SET (name=?, price=?) WHERE id=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update item with id " + item.getId(), e);
        }
        return item;
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM beershop.items where id=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete item with id " + id, e);
        }
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM beershop.items;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                Item item = new Item();
                item.setId(itemId);
                item.setName(name);
                item.setPrice(price);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can not get all items!", e);
        }
        return items;
    }
}
