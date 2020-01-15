package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.exception.AuthenticationException;
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
                .peek(u -> System.out.println(u))
                .filter(u -> u.getId().equals(id))
                .peek(u -> System.out.println(u))
                .findFirst()
                .get());
    }

    @Override
    public void delete(User user) {
        Storage.users.remove(user);
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = Storage.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();

        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Try again");
        }
        return user.get();

    }
}
