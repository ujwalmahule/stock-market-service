package com.ujwal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

}
