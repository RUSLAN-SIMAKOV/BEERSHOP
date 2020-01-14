package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.model.User;

public interface UserService {

    User create(User user);

    String getToken();

    List<User> getAll();

    User get(Long id);

    Optional<User> getByToken(String token);

    User update(User user);

    void delete(Long id);

    void delete(User user);

    User login(String login, String password) throws AuthenticationException;

}
