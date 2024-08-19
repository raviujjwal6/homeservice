package com.tyss.homeservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.homeservice.repositoy.Address_Repository;

@Repository
public class Address_Dao {
	@Autowired
	private Address_Repository address_Repository;

}
