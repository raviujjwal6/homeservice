package com.tyss.homeservice.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tyss.homeservice.dto.Customer;

public interface Customer_Repository extends JpaRepository<Customer, Integer> {
	/*
	 * @Query("Select c from Customer c where c.email=:emailId And c.password=:pass"
	 * ) Customer findByEmail(@Param("emailId") String email,@Param( "pass") String
	 * password);
	 */
	Customer findByEmail( String email);
}
