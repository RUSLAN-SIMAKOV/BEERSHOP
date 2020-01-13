package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService {

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket getByUser(Long userId);

    Bucket update(Bucket bucket);

    void delete(Long id);

    void delete(Bucket bucket);

    Bucket addItem(Long bucketId, Long itemId);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket);

    List<Item> getAllItems(Bucket bucket);
}
