package beershop.service.impl;

import beershop.dao.BucketDao;
import beershop.dao.ItemDao;
import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.lib.Service;
import beershop.model.Bucket;
import beershop.model.Item;
import beershop.service.BucketService;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) throws DataProcessingException {
        return bucketDao.get(id).get();
    }

    @Override
    public Bucket getByUser(Long userId) throws DataProcessingException {
        return bucketDao.getByUser(userId);
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) throws DataProcessingException {
        bucketDao.delete(id);
    }

    @Override
    public void delete(Bucket bucket) throws DataProcessingException {
        bucketDao.delete(bucket);
    }

    @Override
    public Bucket addItem(Bucket bucket, Long itemId) throws DataProcessingException {
        Item item = itemDao.get(itemId).get();
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingException {
        bucketDao.delete(bucket, item);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.getItems();
    }
}
