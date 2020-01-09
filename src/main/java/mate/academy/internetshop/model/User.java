package mate.academy.internetshop.model;

import java.util.List;

public class User {

    private static Long idProducer = 0L;
    private String name;
    private Bucket bucket;
    private List<Order> orderList;
    private Long id;

    public User(String name) {
        this.name = name;
        setId(++idProducer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
