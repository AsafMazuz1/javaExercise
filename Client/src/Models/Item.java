package Models;

// This class is used to store item data
public class Item {

    private long id;
    private String description;
    private double price;

    public Item() {
    }

    public Item(long id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
