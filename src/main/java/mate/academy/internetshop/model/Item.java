package mate.academy.internetshop.model;

public class Item {
    private static Long idProducer = Long.valueOf(0);
    private String name;
    private Integer price;
    private Long id;

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
        setId(++idProducer);
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
}
