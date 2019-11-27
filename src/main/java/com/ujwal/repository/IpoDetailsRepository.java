package com.ujwal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.model.IpoDetails;

@Repository
public interface IpoDetailsRepository extends JpaRepository<IpoDetails, Long> {
	
	Page<IpoDetails> findByCompanyId(long id, PageRequest page);
	
}
