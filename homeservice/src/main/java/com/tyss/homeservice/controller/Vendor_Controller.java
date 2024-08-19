package com.tyss.homeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.homeservice.dto.Vendor;
import com.tyss.homeservice.service.Vendor_Service;
import com.tyss.homeservice.util.ResponseStructure;

@RestController
public class Vendor_Controller {
	@Autowired
	private Vendor_Service service;
	
	@PostMapping("/savevendor")
	public ResponseEntity<ResponseStructure<Vendor>> saveVendor(@RequestBody Vendor vendor) {
		return service.saveVendor(vendor);
	}
	@GetMapping("/findvendor/{id}")
	public ResponseEntity<ResponseStructure<Vendor>> findVendorById(@PathVariable int id) {
		return service.findVendorById(id);
	}
	@PutMapping("/updatevendor")
	public ResponseEntity<ResponseStructure<Vendor>> updateVendorDetails(@RequestBody Vendor vendor) {
		return service.updateVendorDetails(vendor);
	}
	@DeleteMapping("deletevendor/{id}")
	public ResponseEntity<ResponseStructure<Vendor>> deleteVendorById(@PathVariable int id) {
		return service.deletevendorById(id);
	}
	@PostMapping("/vendor/login")
	public ResponseEntity<ResponseStructure<Vendor>> login(@RequestBody Vendor vendor) {
		return service.login(vendor.getEmail(), vendor.getPassword());
	}

}
