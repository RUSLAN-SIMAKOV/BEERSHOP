package beershop.factory;

import beershop.dao.BucketDao;
import beershop.dao.ItemDao;
import beershop.dao.OrderDao;
import beershop.dao.UserDao;
import beershop.dao.jdbc.BucketDaoJdbcImpl;
import beershop.dao.jdbc.ItemDaoJdbcImpl;
import beershop.dao.jdbc.OrderDaoJdbcImpl;
import beershop.dao.jdbc.UserDaoJdbcImpl;
import beershop.service.BucketService;
import beershop.service.ItemService;
import beershop.service.OrderService;
import beershop.service.UserService;
import beershop.service.impl.BucketServiceImpl;
import beershop.service.impl.ItemServiceImpl;
import beershop.service.impl.OrderServiceImpl;
import beershop.service.impl.UserServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Factory {

    private static final Logger LOGGER = Logger.getLogger(Factory.class);
    private static final String URL = "jdbc:mysql://localhost/beershop?";
    private static final String USER = "root";
    private static final String PASSWORD = "1";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL + "user=" + USER + "&password="
                    + PASSWORD + "&serverTimezone=EET");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Can not make connection with DB!", e);
        }
    }

    private static BucketDao instanceBucketDao;
    private static ItemDao instanceItemDao;
    private static OrderDao instanceOrderDao;
    private static UserDao instanceUserDao;

    private static BucketService instanceBucketService;
    private static ItemService instanceItemService;
    private static OrderService instanceOrderService;
    private static UserService instanceUserService;

    public static BucketDao getBucketDao() {
        return instanceBucketDao == null
                ? instanceBucketDao = new BucketDaoJdbcImpl(connection) : instanceBucketDao;
    }

    public static ItemDao getItemDao() {
        return instanceItemDao == null
                ? instanceItemDao = new ItemDaoJdbcImpl(connection) : instanceItemDao;
    }

    public static OrderDao getOrderDao() {
        return instanceOrderDao == null
                ? instanceOrderDao = new OrderDaoJdbcImpl(connection) : instanceOrderDao;
    }

    public static UserDao getUserDao() {
        return instanceUserDao == null
                ? instanceUserDao = new UserDaoJdbcImpl(connection) : instanceUserDao;
    }

    public static BucketService getBucketService() {
        return instanceBucketService == null
                ? instanceBucketService = new BucketServiceImpl() : instanceBucketService;
    }

    public static ItemService getItemService() {
        return instanceItemService == null
                ? instanceItemService = new ItemServiceImpl() : instanceItemService;
    }

    public static OrderService getOrderService() {
        return instanceOrderService == null
                ? instanceOrderService = new OrderServiceImpl() : instanceOrderService;
    }

    public static UserService getUserService() {
        return instanceUserService == null
                ? instanceUserService = new UserServiceImpl() : instanceUserService;
    }
}
