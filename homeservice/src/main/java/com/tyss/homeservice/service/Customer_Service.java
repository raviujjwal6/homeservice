package com.tyss.homeservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.homeservice.dao.Customer_Dao;
import com.tyss.homeservice.dto.Customer;
import com.tyss.homeservice.exception.IdNotFound;
import com.tyss.homeservice.exception.Login_Exception;
import com.tyss.homeservice.util.EmailUtil;
import com.tyss.homeservice.util.ResponseStructure;

@Service
public class Customer_Service {
	@Autowired
	private Customer_Dao customer_Dao;
	@Autowired
	private EmailUtil emailUtil;
	
	private ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		Customer customer2=customer_Dao.saveCustomer(customer);
		structure.setData(customer2);
		emailUtil.sendEmail(customer.getEmail());
		structure.setMsg("Student data saved sucessfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.CREATED);
	}
	
	public Optional<Customer> findCustomerById(int id) {
		 Optional<Customer> optional = customer_Dao.findCustomerById(id);
		if(optional.isPresent()) {
			return optional;
		}else
			throw new IdNotFound();
	}
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
		Customer customer2=customer_Dao.updateCustomer(customer);
		structure.setData(customer2);
		structure.setMsg("data modified");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomerById(int id) {
		Optional<Customer> customer=customer_Dao.findCustomerById(id);
		if(customer.isPresent()) {
			customer_Dao.deleteCustomerById(id);
			structure.setMsg("data deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.OK);
		}else {
			throw new IdNotFound("does not perform delete operation!!! Id not present");
		}
	}
	public ResponseEntity<ResponseStructure<Customer>> login(String email,String password) {
		Customer customer=customer_Dao.findByEmail(email);
		if(customer != null) {
			if(customer.getPassword().equals(password)) {
				structure.setData(customer);
				structure.setMsg("Login sucessfull");
				structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.OK);
			}else {
				/*
				 * structure.setMsg("Password is incorrect");
				 * structure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value()); return new
				 * ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.
				 * NOT_ACCEPTABLE);
				 */
				throw new Login_Exception("Password did not match");
			}
		}else {
			/*
			 * structure.setMsg("incorrect email ");
			 * structure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value()); return new
			 * ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.
			 * NOT_ACCEPTABLE);
			 */
			throw new Login_Exception("incorrect email");
		}
	}
}
