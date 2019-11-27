package com.ujwal.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.exception.ResourceNotFoundException;
import com.ujwal.model.StockPrice;
import com.ujwal.repository.StockPriceRepository;

@RestController
@RequestMapping("/price")
public class StockPriceController {
	
	@Autowired
	StockPriceRepository repository;
	
	@GetMapping("/{id}")
	public StockPrice getStockPrice(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock Price", "id", id));
	}
	
	@GetMapping("/for-company-date-range/{id}/{exId}/{date1}/{date2}/{page}/{size}")
	public Page<StockPrice> getPriceByRange(@PathVariable(value = "id") long companyId, 
											@PathVariable(value = "exId") long exchangeId,
											@PathVariable(value = "date1") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startDate,
											@PathVariable(value = "date2") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate,
											@PathVariable(value = "page") int page, 
											@PathVariable(value = "size") int size) {
		
		return repository.findByCompanyIdAndExchangeIdAndTimestampAfterAndTimestampBeforeOrderByTimestampDesc(companyId, exchangeId, startDate, endDate, PageRequest.of(page, size));
	
	}

}
