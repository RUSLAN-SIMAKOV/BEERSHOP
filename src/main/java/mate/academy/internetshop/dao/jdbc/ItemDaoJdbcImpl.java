package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import mate.academy.internetshop.dao.AbstractDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static final String DB_NAME = "beershop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        Statement statement = null;
        String query = String.format("INSERT INTO %s.items (name, price) VALUES ('%s', %d);",
                DB_NAME, item.getName(), item.getPrice());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Can not create item! ", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can not close statement ", e);
                }
            }
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        Statement statement = null;
        String query = String.format("SELECT * FROM %s.items where id=%d;",
                DB_NAME, id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
            logger.warn("Can not get item with id " + id, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can not close statement ", e);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        Statement statement = null;
        String query = String.format("UPDATE %s.items SET (name='%s', price=%d) WHERE id=%d;",
                DB_NAME, item.getName(), item.getPrice(), item.getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Can not update item with id " + item.getId(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can not close statement ", e);
                }
            }
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        Statement statement = null;
        String query = String.format("DELETE FROM %s.items where id=%d;",
                DB_NAME, id);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Can not delete item with id " + id, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can not close statement ", e);
                }
            }
        }
    }
}
