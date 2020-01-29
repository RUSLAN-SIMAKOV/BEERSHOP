package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.Item;

public interface ItemDao {

    Item create(Item item) throws DataProcessingException;

    Optional<Item> get(Long id) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    List<Item> getAllItems() throws DataProcessingException;
}
