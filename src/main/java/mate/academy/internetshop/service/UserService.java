package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.AuthenticationException;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserService {

    User create(User user) throws DataProcessingException;

    String getToken();

    List<User> getAll() throws DataProcessingException;

    User get(Long id) throws DataProcessingException;

    Optional<User> getByToken(String token) throws DataProcessingException;

    User update(User user) throws DataProcessingException;

    void delete(Long id) throws DataProcessingException;

    User login(String login, String password)
            throws AuthenticationException, DataProcessingException;

}
