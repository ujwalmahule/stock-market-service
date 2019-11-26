package com.ujwal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.exception.ResourceNotFoundException;
import com.ujwal.model.FileRecord;
import com.ujwal.repository.FileRecordRepository;

@RestController
@RequestMapping("/upload-status")
public class UploadStatusController {
	
	@Autowired
	FileRecordRepository repository;
	
	@GetMapping("/{id}")
	public FileRecord getUploadStatus(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FileRecord", "id", id));
	}
}
