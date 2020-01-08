package mate.academy.internetshop.service;

import java.util.Optional;
import mate.academy.internetshop.model.User;

public interface UserService {

    User create(User user);

    Optional<User> get(Long id);

    User update(User user);

    void delete(Long id);

    void delete(User user);
}
