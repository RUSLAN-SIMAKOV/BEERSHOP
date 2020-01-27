package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket, String token);

    Optional<Bucket> get(Long bucketId);

    Bucket getByUser(Long userId);

    Bucket update(Bucket bucket);

    void delete(Long id);

    void delete(Bucket bucket);
}
