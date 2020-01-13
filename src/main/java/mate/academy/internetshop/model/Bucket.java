package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {

    private static Long idProducer = 0L;
    private List<Item> items;
    private Long userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
