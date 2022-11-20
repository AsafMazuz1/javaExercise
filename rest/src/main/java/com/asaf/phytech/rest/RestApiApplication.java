package com.asaf.phytech.rest;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.asaf.phytech.rest.Models.Item;
import com.asaf.phytech.rest.Models.Stock;
import com.asaf.phytech.rest.Repos.ItemRepo;
import com.asaf.phytech.rest.Repos.StockRepo;

@SpringBootApplication
public class RestApiApplication {

	@Autowired
	ItemRepo itemRepo;
	@Autowired
	StockRepo stockRepo;

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	// Seed the database with some items
	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			int max = 10;
			if (itemRepo.count() == 0) {
				HashMap<Integer, Item> mapItem = new HashMap<>();
				HashMap<Integer, Stock> mapStock = new HashMap<>();
				for (int i = 0; i < max; i++) {

					Item item = new Item();
					item.setDescription("Item " + (i + 1));
					item.setPrice(10.0 * i + 1);

					Stock stockItem = new Stock();
					stockItem.setAmount(i);

					mapItem.put(i, item);
					mapStock.put(i, stockItem);

					item.setStock(Collections.singletonList(mapStock.get(i)).get(0));
					stockItem.setItem(Collections.singletonList(mapItem.get(i)).get(0));

					stockRepo.save(stockItem);
					itemRepo.save(item);

				}
			}

		};
	}

}
