package com.tyss.homeservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.homeservice.repositoy.Work_Repository;

@Repository
public class Work_Dao {
	@Autowired
	private Work_Repository work_Repository;

}
