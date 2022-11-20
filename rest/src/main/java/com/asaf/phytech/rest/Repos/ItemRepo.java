package com.asaf.phytech.rest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asaf.phytech.rest.Models.Item;
import com.asaf.phytech.rest.Models.Projections.ItemDescAndPrice;

public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query(value = "SELECT item.* FROM item JOIN stock ON stock.id = item.id WHERE stock.amount > 0", nativeQuery = true)
    public List<ItemDescAndPrice> FindAllInStockQuery();
}
