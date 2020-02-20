package beershop.dao;

import beershop.exception.DataProcessingException;
import beershop.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Item create(Item item) throws DataProcessingException;

    Optional<Item> get(Long id) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    List<Item> getAllItems() throws DataProcessingException;
}
