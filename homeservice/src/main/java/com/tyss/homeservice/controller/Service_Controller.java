package com.tyss.homeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.homeservice.dto.ServiceCost;
import com.tyss.homeservice.dto.Work;
import com.tyss.homeservice.service.ServiceCost_Service;
import com.tyss.homeservice.util.ResponseStructure;

@RestController
public class Service_Controller {
	@Autowired
	private ServiceCost_Service service;
	
	@PutMapping("/start/work/{wid}/{vid}")
	public ResponseEntity<ResponseStructure<Work>> startWork(@PathVariable int wid,@PathVariable int vid) {
		return service.startWork(wid, vid);
	}
	@PostMapping("/end/work/{wid}/{vid}")
	public ResponseEntity<ResponseStructure<Work>> endWork(@PathVariable int wid,@PathVariable int vid) {
		return service.endWork(wid,vid);
	}
	@PostMapping("/payment/{sericeCost_Id}/{mode}")
	public ResponseEntity<ResponseStructure<ServiceCost>> payment(@PathVariable int sericeCost_Id,@PathVariable String mode) {
		return service.payment(sericeCost_Id, mode);
	}

}
