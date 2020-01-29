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
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    @Inject
    private static ItemService itemService;

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {

        String query = "INSERT INTO beershop.buckets (id_user) "
                + "VALUES (?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, bucket.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                long bucketId = resultSet.getLong(1);
                bucket.setId(bucketId);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can not create user! ", e);
        }
        return bucket;
    }

    private Bucket bucketExtractor(Long bucketIdentifier, String query)
            throws DataProcessingException {

        Bucket bucket = new Bucket();
        List<Item> items = new ArrayList<>();

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, bucketIdentifier);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long bucketId = resultSet.getLong("id_buckets");
                long userId = resultSet.getLong("id_user");
                long itemId = resultSet.getLong("id_item");
                bucket.setId(bucketId);
                bucket.setUserId(userId);
                items.add(itemService.get(itemId));
            }
            bucket.setItems(items);
        } catch (SQLException | DataProcessingException e) {
            throw new DataProcessingException("Can not get bucket! ", e);
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) throws DataProcessingException {
        String query = "SELECT * FROM beershop.buckets "
                + "LEFT JOIN beershop.item_bucket ON buckets.id_buckets = item_bucket.id_bucket "
                + "where id_bucket=?;";
        return Optional.of(bucketExtractor(bucketId, query));
    }

    @Override
    public Bucket getByUser(Long userId) throws DataProcessingException {

        String query = "SELECT * FROM beershop.buckets "
                + "LEFT JOIN beershop.item_bucket ON buckets.id_buckets = item_bucket.id_bucket "
                + "where id_user=?;";
        return bucketExtractor(userId, query);
    }

    private void deleteOldBucket(Long bucketId) throws DataProcessingException {
        String queryDeleteBucket = "DELETE FROM beershop.item_bucket where id_bucket=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteBucket)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not update(delete) bucket with id "
                    + bucketId, e);
        }
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {

        deleteOldBucket(bucket.getId());

        for (Item item : bucket.getItems()) {
            String query = "INSERT INTO beershop.item_bucket (id_bucket, id_item) "
                    + "VALUES (?, ?);";
            try (PreparedStatement statement
                         = connection.prepareStatement(query)) {
                statement.setLong(1, bucket.getId());

                statement.setLong(2, item.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Can not update item! ", e);
            }
        }
        return bucket;
    }

    @Override
    public void delete(Long id) throws DataProcessingException {

        deleteOldBucket(id);

        String queryDeleteBucket = "DELETE FROM beershop.buckets where id_buckets=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteBucket)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not delete bucket with id " + id, e);
        }
    }

    @Override
    public void delete(Bucket bucket) throws DataProcessingException {
        delete(bucket.getId());
    }

    @Override
    public void delete(Bucket bucket, Item item) throws DataProcessingException {

        String query = "DELETE FROM beershop.item_bucket where id_bucket=? AND id_item=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucket.getId());
            statement.setLong(2, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not delete bucket with id " + bucket.getId(), e);
        }
    }
}
