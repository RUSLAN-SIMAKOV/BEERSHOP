package beershop.dao.impl;

import beershop.dao.ItemDao;
import beershop.db.Storage;
import beershop.lib.Dao;
import beershop.model.Item;
import java.util.List;
import java.util.Optional;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public Item update(Item item) {
        Item updatedItem = get(item.getId()).get();
        updatedItem.setName(item.getName());
        updatedItem.setPrice(item.getPrice());
        return updatedItem;
    }

    @Override
    public void delete(Long id) {
        Storage.items.remove(Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get());
    }

    @Override
    public List getAllItems() {
        return Storage.items;
    }
}
