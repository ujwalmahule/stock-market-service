package com.ujwal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.id.ExchangeCompanyId;
import com.ujwal.model.ExchangeToCompany;

@Repository
public interface ExchangeToCompanyRepository extends JpaRepository<ExchangeToCompany, ExchangeCompanyId> {
	public ExchangeToCompany findByStockCodeEquals(String code);
}
