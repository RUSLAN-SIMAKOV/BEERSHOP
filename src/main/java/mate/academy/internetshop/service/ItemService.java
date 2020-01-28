package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.Item;

public interface ItemService {

    Item create(Item item) throws DataProcessingException;

    Item get(Long id) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    List getAllItems() throws DataProcessingException;
}
