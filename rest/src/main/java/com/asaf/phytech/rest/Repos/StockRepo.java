package com.asaf.phytech.rest.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asaf.phytech.rest.Models.Stock;

public interface StockRepo extends JpaRepository<Stock, Long> {

}
