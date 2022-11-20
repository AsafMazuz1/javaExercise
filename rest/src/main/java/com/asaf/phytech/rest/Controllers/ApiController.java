package com.asaf.phytech.rest.Controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RestController;

import com.asaf.phytech.rest.Repos.ItemRepo;
import com.google.gson.Gson;
import com.asaf.phytech.rest.Models.Item;
import com.asaf.phytech.rest.Models.Projections.ItemDescAndPrice;

@RestController
public class ApiController {

    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("/")
    public String getPage() {
        return "Hello World";
    }

    @GetMapping("/api/items/stock")
    public List<ItemDescAndPrice> getItems() {
        return itemRepo.FindAllInStockQuery();
    }

    @GetMapping("/api/items/count")
    public long getItemsCount() {
        return itemRepo.count();
    }

    @GetMapping("/api/items/{id}/price")
    public Double getItemPrice(@PathVariable long id) {
        if (itemRepo.existsById(id)) {
            Item item = itemRepo.findById(id).get();
            return item.getPrice();
        }

        return null;

    }

    @PutMapping("/api/items/{id}/buy")
    public String buyItem(@PathVariable long id) {
        HashMap<String, String> map = new HashMap<>();
        // Check if item exists
        if (itemRepo.existsById(id)) {
            Item item = itemRepo.findById(id).get();
            // Check if item is in stock
            if (item.getStock().getAmount() > 0) {
                // Decrease stock amount
                item.getStock().setAmount(item.getStock().getAmount() - 1);
                itemRepo.save(item);
                // Return success message
                map.put("status", "1");
                map.put("message", "Item " + item.getId() + " bought successfully");

            } else {
                // Return error message - item not in stock
                map.put("status", "0");
                map.put("message", "Item " + item.getId() + " is out of stock");

            }
        } else {
            // Return error message - item not found
            map.put("status", "0");
            map.put("message", "Item " + id + " not found");
        }
        // Return json
        Gson gson = new Gson();
        String json = gson.toJson(map);

        return json;

    }

}
