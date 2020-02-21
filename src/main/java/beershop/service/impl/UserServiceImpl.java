package beershop.service.impl;

import beershop.dao.UserDao;
import beershop.exception.AuthenticationException;
import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.lib.Service;
import beershop.model.User;
import beershop.service.UserService;
import beershop.util.HashUtil;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws DataProcessingException {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        user.setToken(getToken());
        return userDao.create(user);
    }

    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        return userDao.getAll();
    }

    @Override
    public User get(Long id) throws DataProcessingException {
        return userDao.get(id).get();
    }

    @Override
    public Optional<User> getByToken(String token) throws DataProcessingException {
        return userDao.getByToken(token);
    }

    @Override
    public User update(User user) throws DataProcessingException {
        return userDao.update(user);
    }

    @Override
    public void delete(Long id) throws DataProcessingException {
        userDao.delete(id);
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException, DataProcessingException {

        Optional<User> user = userDao.findByLogin(login);

        if (user.isEmpty() || !user.get().getPassword()
                .equals(HashUtil.hashPassword(password, user.get().getSalt()))) {
            throw new AuthenticationException("Try again");
        }
        return user.get();
    }
}
