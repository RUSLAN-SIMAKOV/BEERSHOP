package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;

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
}
