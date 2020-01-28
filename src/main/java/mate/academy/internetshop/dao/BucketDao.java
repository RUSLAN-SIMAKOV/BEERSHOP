package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketDao {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Optional<Bucket> get(Long bucketId) throws DataProcessingException;

    Bucket getByUser(Long userId) throws DataProcessingException;

    Bucket update(Bucket bucket) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    void delete(Bucket bucket) throws DataProcessingException;

    void delete(Bucket bucket, Item item) throws DataProcessingException;
}
