package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserDao {

    User create(User user) throws DataProcessingException;

    Optional<User> get(Long id) throws DataProcessingException;

    Optional<User> getByToken(String token) throws DataProcessingException;

    User update(User user) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    Optional<User> findByLogin(String login) throws DataProcessingException;

    List<User> getAll() throws DataProcessingException;
}
