package beershop.dao;

import beershop.exception.DataProcessingException;
import beershop.model.Bucket;
import beershop.model.Item;
import java.util.Optional;

public interface BucketDao {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Optional<Bucket> get(Long bucketId) throws DataProcessingException;

    Bucket getByUser(Long userId) throws DataProcessingException;

    Bucket update(Bucket bucket) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    void delete(Bucket bucket) throws DataProcessingException;

    void delete(Bucket bucket, Item item) throws DataProcessingException;
}
