package com.asaf.phytech.rest.Models;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String description;

    @Column
    private double price;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY)
    private Stock stock;

    public Item() {

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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", description=" + description + ", price=" + price + ", stock=" + stock + "]";
    }

}
