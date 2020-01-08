package mate.academy.internetshop.model;

import java.util.List;

public class Order {

    private static Long idProducer = Long.valueOf(0);
    private List<Item> items;
    private User user;
    private Long id;

    public Order() {
        setId(++idProducer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
