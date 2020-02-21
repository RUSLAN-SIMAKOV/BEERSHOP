package beershop.lib;

import beershop.dao.BucketDao;
import beershop.dao.ItemDao;
import beershop.dao.OrderDao;
import beershop.dao.UserDao;
import beershop.factory.Factory;
import beershop.service.BucketService;
import beershop.service.ItemService;
import beershop.service.OrderService;
import beershop.service.UserService;
import java.util.HashMap;
import java.util.Map;

public class AnnotatedClassMap {

    protected static final Map<Class, Object> classMap = new HashMap<>();

    static  {

        classMap.put(BucketDao.class, Factory.getBucketDao());
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
        classMap.put(UserDao.class, Factory.getUserDao());

        classMap.put(BucketService.class, Factory.getBucketService());
        classMap.put(ItemService.class, Factory.getItemService());
        classMap.put(OrderService.class, Factory.getOrderService());
        classMap.put(UserService.class, Factory.getUserService());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
