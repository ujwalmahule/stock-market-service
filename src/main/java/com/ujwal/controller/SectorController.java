package com.ujwal.controller;

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
import com.ujwal.model.Sector;
import com.ujwal.repository.SectorRepository;

@RestController
@RequestMapping("/sector")
public class SectorController {
	
	@Autowired
	SectorRepository repository;
	
	@GetMapping("/{id}")
	public Sector getSector(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sector", "id", id));
	}
	
	@PostMapping("/")
	public Sector createSector(@Valid @RequestBody Sector exchange) {
		return repository.save(exchange);
	}
	
	@PutMapping("/{id}")
	public Sector updateSector(@PathVariable Long id, @Valid @RequestBody Sector sector) {
		return repository.findById(id).map(foundSector -> {
			foundSector.setName(sector.getName());
			return repository.save(foundSector);
		}).orElseThrow(() -> new ResourceNotFoundException("Sector", "id", id));
	}
}
