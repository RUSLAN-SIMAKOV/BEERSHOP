package beershop.dao.jdbc;

import beershop.dao.AbstractDao;
import beershop.dao.UserDao;
import beershop.exception.DataProcessingException;
import beershop.lib.Dao;
import beershop.model.Role;
import beershop.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) throws DataProcessingException {

        String query = "INSERT INTO beershop.users (name, surname, login, password, token, salt) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setBytes(6, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                long userId = resultSet.getLong(1);
                user.setId(userId);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can not create user! ", e);
        }
        return user;
    }

    private User dataBaseUserExtractor(ResultSet resultSet) throws SQLException {

        User user = null;

        while (resultSet.next()) {
            if (user == null) {
                user = new User();
                long userId = resultSet.getLong("id_user");
                user.setId(userId);
                String name = resultSet.getString("name");
                user.setName(name);
                String surname = resultSet.getString("surname");
                user.setSurname(surname);
                String login = resultSet.getString("login");
                user.setLogin(login);
                String password = resultSet.getString("password");
                user.setPassword(password);
                String token = resultSet.getString("token");
                user.setToken(token);
                byte[] salt = resultSet.getBytes("salt");
                user.setSalt(salt);
            }
            user.addRole(Role.of(resultSet.getString("role-name")));
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) throws DataProcessingException {

        String query = "SELECT * FROM beershop.users "
                + "JOIN beershop.users_roles ON users.id_user = users_roles.id_user "
                + "JOIN beershop.roles ON users_roles.id_role = roles.id_role "
                + "WHERE users.id_user=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(dataBaseUserExtractor(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("Can not get user! ", e);
        }
    }

    @Override
    public Optional<User> getByToken(String token) throws DataProcessingException {

        String query = "SELECT * FROM beershop.users "
                + "JOIN beershop.users_roles ON users.id_user = users_roles.id_user "
                + "JOIN beershop.roles ON users_roles.id_role = roles.id_role "
                + "WHERE users.token=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setString(1, token);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(dataBaseUserExtractor(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("Can not get user! ", e);
        }
    }

    @Override
    public User update(User user) throws DataProcessingException {

        String query = "UPDATE beershop.users "
                + "SET (name=?, surname=?, login=?, password=?, token=?, salt=?) WHERE id_user=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setBytes(6, user.getSalt());
            statement.setLong(7, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not update item with id " + user.getId(), e);
        }
        return user;
    }

    @Override
    public void delete(Long id) throws DataProcessingException {

        String query = "DELETE FROM beershop.users_roles where id_user=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not delete user with id " + id, e);
        }
        String queryDeleteUser = "DELETE FROM beershop.users where id_user=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteUser)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can not delete user with id " + id, e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DataProcessingException {

        String query = "SELECT * FROM beershop.users "
                + "JOIN beershop.users_roles ON users.id_user = users_roles.id_user "
                + "JOIN beershop.roles ON users_roles.id_role = roles.id_role "
                + "WHERE users.login=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(dataBaseUserExtractor(resultSet));
        } catch (SQLException e) {
            throw new DataProcessingException("Can not get user! ", e);
        }
    }

    @Override
    public List<User> getAll() throws DataProcessingException {

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM beershop.users;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long userId = resultSet.getLong("id_user");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                byte[] salt = resultSet.getBytes("salt");
                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setPassword(password);
                user.setToken(token);
                user.setSalt(salt);

                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can not get all users!", e);
        }
        return users;
    }
}
