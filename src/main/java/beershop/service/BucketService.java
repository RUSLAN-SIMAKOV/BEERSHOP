package beershop.service;

import beershop.exception.DataProcessingException;
import beershop.model.Bucket;
import beershop.model.Item;
import java.util.List;

public interface BucketService {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Bucket get(Long id) throws DataProcessingException;

    Bucket getByUser(Long userId) throws DataProcessingException;

    Bucket update(Bucket bucket) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    void delete(Bucket bucket) throws DataProcessingException;

    Bucket addItem(Bucket bucket, Long itemId) throws DataProcessingException;

    void deleteItem(Bucket bucket, Item item) throws DataProcessingException;

    List<Item> getAllItems(Bucket bucket);
}
