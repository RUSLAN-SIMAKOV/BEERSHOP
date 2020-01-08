package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can not find item with ID " + id));
    }

    @Override
    public Item update(Item item) {
        Item updatedItem = get(item.getId());
        updatedItem.setName(item.getName());
        updatedItem.setPrice(item.getPrice());
        return updatedItem;
    }

    @Override
    public void delete(Long id) {
        Storage.items.remove(Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst());
    }

    @Override
    public void delete(Item item) {
        Storage.items.remove(item);
    }
}
