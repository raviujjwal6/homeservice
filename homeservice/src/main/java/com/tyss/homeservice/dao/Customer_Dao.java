package com.tyss.homeservice.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.homeservice.dto.Customer;
import com.tyss.homeservice.repositoy.Customer_Repository;

@Repository
public class Customer_Dao {
	@Autowired
	private Customer_Repository customer_Repository;
	
	public Customer saveCustomer(Customer customer) {
		return customer_Repository.save(customer);
	}
	
	public Optional<Customer> findCustomerById(int id) {
		 return customer_Repository.findById(id);
		 
	}
	public Customer updateCustomer(Customer customer) {
		return customer_Repository.save(customer);
	}
	
	public void deleteCustomerById(int id) {
		 customer_Repository.deleteById(id);
	}
	public Customer findByEmail(String email) {
		Customer customer= customer_Repository.findByEmail(email);
		System.out.println(customer);
		if(customer != null) {
			return customer;
		}else
			return null;
	}

}
