package com.ujwal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.exception.ResourceNotFoundException;
import com.ujwal.model.Company;
import com.ujwal.model.ExchangeToCompany;
import com.ujwal.repository.CompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyRepository repository;
	
	@GetMapping("/{id}")
	public Company getCompany(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
	}
	
	@PostMapping("/")
	public Company createCompany(@Valid @RequestBody Company company) {
		return repository.save(company);
	}
	
	@PutMapping("/{id}")
	public Company updateCompany(@PathVariable Long id, @Valid @RequestBody Company company) {
		return repository.findById(id).map(foundCompany -> {
			updateCompany(foundCompany, company);
			return repository.save(foundCompany);
		}).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
	}

	private void updateCompany(Company foundCompany, @Valid Company company) {
		foundCompany.setBoardOfDirectors(company.getBoardOfDirectors());
		foundCompany.setBriefWriteup(company.getBriefWriteup());
		foundCompany.setCeo(company.getCeo());
		foundCompany.setCompanyName(company.getCompanyName());
		foundCompany.setSector(company.getSector());
		foundCompany.setTurnover(company.getTurnover());
		
		List<ExchangeToCompany> exchangeList = company.getExchange();
		if(exchangeList!=null && !exchangeList.isEmpty()) { 
			foundCompany.setExchange(company.getExchange());
		}
	}

}
