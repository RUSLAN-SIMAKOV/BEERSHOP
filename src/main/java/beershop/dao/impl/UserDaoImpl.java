package beershop.dao.impl;

import beershop.dao.UserDao;
import beershop.db.Storage;
import beershop.lib.Dao;
import beershop.model.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(u -> u.getToken().equals(token)).findFirst();
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
                .peek(System.out::println)
                .filter(u -> u.getId().equals(id))
                .peek(System.out::println)
                .findFirst()
                .get());
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Storage.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }
}
