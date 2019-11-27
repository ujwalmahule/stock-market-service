package com.ujwal.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
	
	//Page<StockPrice> findByCompanyIdAndTimestampAfterAndTimestampBeforeOrderByTimestampDesc(long id, Calendar t1, Calendar t2, PageRequest page);

	Page<StockPrice> findByCompanyIdAndStockExchangeIdAndTimestampAfterAndTimestampBeforeOrderByTimestampDesc(long companyId,
			long exchangeId, LocalDate startDate, LocalDate endDate, PageRequest page);
	
}
