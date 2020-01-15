package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    Optional<User> get(Long id);

    Optional<User> getByToken(String token);

    User update(User user);

    void delete(Long id);

    void delete(User user);

    Optional<User> findByLogin(String login);
}
