package mate.academy.internetshop.main;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Main {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Item lightBeer = new Item("Svetloe", 1200);
        Item darkBeer = new Item("Temnoe", 2000);
        itemService.create(lightBeer);
        itemService.create(darkBeer);

        User drinker1 = new User("TestName1");
        User drinker2 = new User("TestName2");
        userService.create(drinker1);
        userService.create(drinker2);
        userService.delete(drinker2);

        Bucket bucket = new Bucket();
        bucketService.create(bucket);

        bucketService.addItem(bucket.getId(), lightBeer.getId());
        bucketService.addItem(bucket.getId(), darkBeer.getId());

        orderService.completeOrder(bucketService.getAllItems(bucket), drinker1);

    }
}
