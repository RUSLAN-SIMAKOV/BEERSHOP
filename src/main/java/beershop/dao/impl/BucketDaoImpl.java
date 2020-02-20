package beershop.dao.impl;

import beershop.dao.BucketDao;
import beershop.db.Storage;
import beershop.exception.DataProcessingException;
import beershop.lib.Dao;
import beershop.model.Bucket;
import beershop.model.Item;
import java.util.Optional;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return Storage.buckets.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public Bucket getByUser(Long userId) {
        return Storage.users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst().get().getBucket();
    }

    @Override
    public Bucket update(Bucket bucket) {
        Bucket updatedBucket = get(bucket.getId()).get();
        updatedBucket.setItems(bucket.getItems());
        return updatedBucket;
    }

    @Override
    public void delete(Long id) {
        Storage.buckets.remove(Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst());
    }

    @Override
    public void delete(Bucket bucket) {
        Storage.buckets.remove(bucket);
    }

    @Override
    public void delete(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().remove(item);
    }
}
