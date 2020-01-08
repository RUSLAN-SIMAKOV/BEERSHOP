package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BucketDaoImpl;
import mate.academy.internetshop.dao.impl.ItemDaoImpl;
import mate.academy.internetshop.dao.impl.OrderDaoImpl;
import mate.academy.internetshop.dao.impl.UserDaoImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

public class Factory {

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
                ? instanceItemDao = new ItemDaoImpl() : instanceItemDao;
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
