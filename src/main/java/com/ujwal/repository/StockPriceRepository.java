package com.ujwal.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
	
	//Page<StockPrice> findByCompanyIdAndTimestampAfterAndTimestampBeforeOrderByTimestampDesc(long id, Calendar t1, Calendar t2, PageRequest page);

	List<StockPrice> findByCompanyIdEqualsAndStockExchangeIdEqualsAndTimestampBetweenOrderByTimestampAsc(
			long companyId, long exchangeId, Calendar startDate, Calendar endDate);
	
}
