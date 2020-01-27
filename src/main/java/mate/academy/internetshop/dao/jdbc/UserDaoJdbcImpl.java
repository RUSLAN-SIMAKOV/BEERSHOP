package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.AbstractDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {

    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {

        String query = "INSERT INTO beershop.users (name, surname, login, password, token) "
                + "VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                long userId = resultSet.getLong(1);
                user.setId(userId);
            }
        } catch (SQLException e) {
            logger.error("Can not create user! ", e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {

        User user = new User();
        String query = "SELECT * FROM beershop.users "
                + "INNER JOIN beershop.roles ON users.id_user = roles.id_user where id=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("id_user");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                String roleAdmin = resultSet.getString("role_second");
                user.setId(userId);
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setPassword(password);
                user.setToken(token);
                if ("ADMIN".equals(roleAdmin)) {
                    Role roleAd = new Role();
                    roleAd.setRoleName(Role.RoleName.ADMIN);
                    user.addRole(roleAd);
                }
            }
        } catch (SQLException e) {
            logger.error("Can not get user! ", e);
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> getByToken(String token) {

        User user = new User();
        String query = "SELECT * FROM beershop.users "
                + "LEFT JOIN beershop.users_roles ON users.id_user = users_roles.id_user where token=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setString(1, token);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("id_user");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                token = resultSet.getString("token");
                String roleAdmin = resultSet.getString("role_second");
                user.setId(userId);
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setPassword(password);
                user.setToken(token);
                if ("ADMIN".equals(roleAdmin)) {
                    Role roleAd = new Role();
                    roleAd.setRoleName(Role.RoleName.ADMIN);
                    user.addRole(roleAd);
                }
            }
        } catch (SQLException e) {
            logger.error("Can not get user! ", e);
        }
        return Optional.of(user);
    }

    @Override
    public User update(User user) {

        String query = "UPDATE beershop.users "
                + "SET (name=?, surname=?, login=?, password+?, token=?) WHERE id=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update item with id " + user.getId(), e);
        }
        return user;
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM beershop.roles where id_user=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete user with id " + id, e);
        }
        String queryDeleteUser = "DELETE FROM beershop.users where id_user=?;";

        try (PreparedStatement statement = connection.prepareStatement(queryDeleteUser)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete user with id " + id, e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {

        User user = new User();
        String query = "SELECT * FROM beershop.users "
                + "LEFT JOIN beershop.users_roles ON users.id_user = users_roles.id_user where login=?;";

        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("id_user");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                String roleAdmin = resultSet.getString("role_second");
                user.setId(userId);
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setPassword(password);
                user.setToken(token);
                if ("ADMIN".equals(roleAdmin)) {
                    Role roleAd = new Role();
                    roleAd.setRoleName(Role.RoleName.ADMIN);
                    user.addRole(roleAd);
                }
            }
        } catch (SQLException e) {
            logger.error("Can not get user! ", e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {

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
                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setPassword(password);
                user.setToken(token);

                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can not get all users!", e);
        }
        return users;
    }
}
