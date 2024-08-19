package com.tyss.homeservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.homeservice.repositoy.ServiceCost_Repository;

@Repository
public class ServiceCost_Dao {
	@Autowired
	private ServiceCost_Repository serviceCost_Repository;

}
