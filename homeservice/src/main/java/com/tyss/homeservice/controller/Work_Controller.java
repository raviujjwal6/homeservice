package com.tyss.homeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.homeservice.dto.Work;
import com.tyss.homeservice.service.Work_Service;
import com.tyss.homeservice.util.ResponseStructure;

@RestController
public class Work_Controller {
	
	@Autowired
	private Work_Service service;
	
	@PostMapping("savework/{cid}")
	public ResponseEntity<ResponseStructure<Work>> saveWork(@PathVariable int cid,@RequestBody Work work) {
		return service.saveWork(cid, work);
	}
	@GetMapping("fetch/work/{wtype}")
	public ResponseEntity<ResponseStructure<List<Work>>> fetchWorkByType(@PathVariable String wtype) {
		return service.fetchWorkByType(wtype);
	}
	@PatchMapping("update/work")
	public ResponseEntity<ResponseStructure<Work>> updateWork(@RequestBody Work work) {
		return service.updateWork(work);
	}
}
