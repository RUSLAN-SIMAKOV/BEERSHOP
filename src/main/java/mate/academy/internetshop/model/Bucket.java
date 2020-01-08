package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {

    private static Long idProducer = 0L;
    private List<Item> items;
    private Long orderId;
    private Long id;

    public Bucket() {
        setId(++idProducer);
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
