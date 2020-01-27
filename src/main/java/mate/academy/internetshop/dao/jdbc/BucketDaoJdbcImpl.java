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
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket, String token) {

        String query = "INSERT INTO beershop.buckets (id_user) "
                + "VALUES (?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userService.getByToken(token).get().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                long bucketId = resultSet.getLong(1);
                bucket.setId(bucketId);
            }
        } catch (SQLException e) {
            logger.error("Can not create user! ", e);
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {

        Bucket bucket = new Bucket();
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM beershop.buckets "
                + "INNER JOIN beershop.item_bucket ON buckets.id_buckets = item_bucket.id_bucket "
                + "where id_bucket=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bucketId = resultSet.getLong("id_buckets");
                long userId = resultSet.getLong("id_user");
                long itemId = resultSet.getLong("id_item");
                bucket.setId(bucketId);
                bucket.setUserId(userId);
                items.add(itemService.get(itemId));
            }
            bucket.setItems(items);
        } catch (SQLException e) {
            logger.error("Can not get bucket! ", e);
        }
        return Optional.of(bucket);
    }

    @Override
    public Bucket getByUser(Long userId) {

        Bucket bucket = new Bucket();
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM beershop.buckets "
                + "INNER JOIN beershop.item_bucket ON buckets.id_buckets = item_bucket.id_bucket "
                + "where id_user=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long bucketId = resultSet.getLong("id_buckets");
                userId = resultSet.getLong("id_user");
                long itemId = resultSet.getLong("id_item");
                bucket.setId(bucketId);
                bucket.setUserId(userId);
                items.add(itemService.get(itemId));
            }
            bucket.setItems(items);
        } catch (SQLException e) {
            logger.error("Can not get bucket! ", e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Item updatedItem = null;
        String query = "INSERT INTO beershop.buckets (id_bucket, id_item) "
                + "VALUES (?, ?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, bucket.getId());
            for (Item item : bucket.getItems()) {
                updatedItem = item;
            }
            statement.setLong(2, updatedItem.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update item! ", e);
        }
        return bucket;
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM beershop.item_bucket where id_bucket=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete bucket with id " + id, e);
        }
        String queryDeleteBucket = "DELETE FROM beershop.buckets where id_buckets=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteBucket)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete bucket with id " + id, e);
        }
    }

    @Override
    public void delete(Bucket bucket) {

        String query = "DELETE FROM beershop.item_bucket where id_bucket=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete bucket with id " + bucket.getId(), e);
        }
        String queryDeleteBucket = "DELETE FROM beershop.buckets where id_buckets=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteBucket)) {
            statement.setLong(1, bucket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete bucket with id " + bucket.getId(), e);
        }
    }
}
