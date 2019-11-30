package com.ujwal.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.exception.ResourceNotFoundException;
import com.ujwal.id.ExchangeCompanyId;
import com.ujwal.model.Company;
import com.ujwal.model.ExchangeToCompany;
import com.ujwal.repository.CompanyRepository;
import com.ujwal.repository.ExchangeToCompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyRepository repository;
	
	@Autowired
	ExchangeToCompanyRepository e2cRepo;
	
	@GetMapping("/{page}/{size}")
	public Page<Company> getCompany(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return repository.findAllByOrderByCompanyName(PageRequest.of(page, size));
	}
	
	@GetMapping("/{id}")
	public Company getCompany(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
	}
	
	@GetMapping("/name/{name}")
	public Company getCompanyByName(@PathVariable(value = "name") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
	}
	
	@GetMapping("/search/{txt}")
	public List<Company> getCompany(@PathVariable(value = "txt") String txt) {
		return repository.findFirst15ByCompanyNameContainsIgnoreCaseOrderByCompanyName(txt);
	}
	
	@PostMapping("/")
	public Company createCompany(@Valid @RequestBody Company company) {
		return repository.save(company);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public Company updateCompany(@PathVariable Long id, @Valid @RequestBody Company company) {
		return repository.findById(id).map(foundCompany -> {
			
			//check if new exchange added
			for(ExchangeToCompany e2c : company.getExchange()) {
				Optional<ExchangeToCompany> founde2c = e2cRepo.findById(new ExchangeCompanyId(e2c.getExchangeId(), e2c.getCompanyId()));
				if(!founde2c.isPresent()) {
					e2cRepo.save(e2c);
				}
			}
			
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
