package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Bucket get(Long id) throws DataProcessingException;

    Bucket getByUser(Long userId) throws DataProcessingException;

    Bucket update(Bucket bucket) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    void delete(Bucket bucket) throws DataProcessingException;

    Bucket addItem(Bucket bucket, Long itemId) throws DataProcessingException;

    void deleteItem(Bucket bucket, Item item) throws DataProcessingException;

    void clear(Bucket bucket);

    List<Item> getAllItems(Bucket bucket);
}
