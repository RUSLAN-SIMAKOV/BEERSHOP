package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public User update(User user) {
        User updatedUser = get(user.getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find user to update"));
        updatedUser.setName(user.getName());
        updatedUser.setBucket(user.getBucket());
        updatedUser.setOrderList(user.getOrderList());
        return updatedUser;
    }

    @Override
    public void delete(Long id) {

        Storage.users.remove(Storage.users
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst());
    }

    @Override
    public void delete(User user) {
        Storage.users.remove(user);
    }
}