package beershop.service;

import beershop.exception.AuthenticationException;
import beershop.exception.DataProcessingException;
import beershop.model.User;
import java.util.List;
import java.util.Optional;

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
