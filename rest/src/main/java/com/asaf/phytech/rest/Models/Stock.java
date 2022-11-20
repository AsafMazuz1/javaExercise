package com.asaf.phytech.rest.Models;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Stock {

    @Id
    private long id;

    @Column
    private int amount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Item item;

    public Stock() {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setId(long id) {
        this.id = id;
    }
}
