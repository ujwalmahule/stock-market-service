package com.ujwal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	@GetMapping("/")
	public List<FileRecord> getUploadStatus() {
		return repository.findAll();
	}
	
	@GetMapping("/{page}/{size}")
	public Page<FileRecord> getUploadStatus(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return repository.findAllByOrderByIdDesc(PageRequest.of(page, size));
	}
	
	@GetMapping("/{id}")
	public FileRecord getUploadStatus(@PathVariable(value = "id") long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FileRecord", "id", id));
	}
}
