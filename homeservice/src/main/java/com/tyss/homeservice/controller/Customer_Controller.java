package com.tyss.homeservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.homeservice.dto.Customer;
import com.tyss.homeservice.service.Customer_Service;
import com.tyss.homeservice.util.ResponseStructure;

@RestController
public class Customer_Controller {
	@Autowired
	private Customer_Service service;
	
	@PostMapping("/savecustomer")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	@GetMapping("/findcustomer/{id}")
	public Optional<Customer> findCustomerById(@PathVariable int id) {
		return service.findCustomerById(id);
	}
	@PutMapping("/updatecustomer")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomerById(@PathVariable int id) {
		return service.deleteCustomerById(id);
	}
	@PostMapping("/customer/login")
	public ResponseEntity<ResponseStructure<Customer>> login(@RequestBody Customer customer) {
		return service.login(customer.getEmail(), customer.getPassword());
	}

}