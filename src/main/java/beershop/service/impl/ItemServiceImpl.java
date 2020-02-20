package beershop.service.impl;

import beershop.dao.ItemDao;
import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.lib.Service;
import beershop.model.Item;
import beershop.service.ItemService;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) throws DataProcessingException {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) throws DataProcessingException {
        return itemDao.get(id).get();
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        return itemDao.update(item);
    }

    @Override
    public void delete(Long id) throws DataProcessingException {
        itemDao.delete(id);
    }

    @Override
    public List getAllItems() throws DataProcessingException {
        return itemDao.getAllItems();
    }

}
