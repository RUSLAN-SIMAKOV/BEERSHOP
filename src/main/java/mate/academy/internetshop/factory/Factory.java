package mate.academy.internetshop.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BucketDaoImpl;
import mate.academy.internetshop.dao.impl.OrderDaoImpl;
import mate.academy.internetshop.dao.impl.UserDaoImpl;
import mate.academy.internetshop.dao.jdbc.ItemDaoJdbcImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {

    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/beershop?"
                    + "user=root&password=1&serverTimezone=UTC");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Can not make connection with DB!", e);
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
                ? instanceBucketDao = new BucketDaoImpl() : instanceBucketDao;
    }

    public static ItemDao getItemDao() {
        return instanceItemDao == null
                ? instanceItemDao = new ItemDaoJdbcImpl(connection) : instanceItemDao;
    }

    public static OrderDao getOrderDao() {
        return instanceOrderDao == null
                ? instanceOrderDao = new OrderDaoImpl() : instanceOrderDao;
    }

    public static UserDao getUserDao() {
        return instanceUserDao == null
                ? instanceUserDao = new UserDaoImpl() : instanceUserDao;
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
