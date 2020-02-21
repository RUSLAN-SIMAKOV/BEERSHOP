package beershop.service;

import beershop.exception.DataProcessingException;
import beershop.model.Item;
import java.util.List;

public interface ItemService {

    Item create(Item item) throws DataProcessingException;

    Item get(Long id) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    List getAllItems() throws DataProcessingException;
}
