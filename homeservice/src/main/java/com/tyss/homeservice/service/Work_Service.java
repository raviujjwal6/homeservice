package com.tyss.homeservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.homeservice.dao.Customer_Dao;
import com.tyss.homeservice.dao.Work_Dao;
import com.tyss.homeservice.dto.Customer;
import com.tyss.homeservice.dto.Work;
import com.tyss.homeservice.exception.IdNotFound;
import com.tyss.homeservice.util.ResponseStructure;

@Service
public class Work_Service {
	@Autowired
	private Work_Dao dao;
	private ResponseStructure<Work> structure = new ResponseStructure<Work>();;
	@Autowired
	private Customer_Dao customer_Dao;

	public ResponseEntity<ResponseStructure<Work>> saveWork(int cid, Work work) {
		Optional<Customer> customer = customer_Dao.findCustomerById(cid);
		if (customer.isPresent()) {
			Customer db = customer.get();
			work.setCustomer(db);
			Work dbwork = dao.saveWork(work);
			List<Work> list = new ArrayList<Work>();
			list.add(dbwork);
			db.setWorks(list);
			
			customer_Dao.updateCustomer(db);
			structure.setData(work);
			structure.setMsg("work saved");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.ACCEPTED);
		} else {
			throw new IdNotFound("Customer id not found to save work");
		}
	}

	public ResponseEntity<ResponseStructure<List<Work>>> fetchWorkByType(String wtype) {
		List<Work> list = dao.fetchWorkByType(wtype);
		if (list != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<List<Work>>();
			structure.setData(list);
			structure.setMsg("All work fetched");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);

		} else
			throw new IdNotFound("Work not present");
	}
	
	public ResponseEntity<ResponseStructure<Work>> updateWork(Work work) {
		Work work2=dao.updateWork(work.getWid(),work);
		if(work2 != null) {
			structure.setData(work2);
			structure.setMsg("Work found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Work>>(structure,HttpStatus.FOUND);
		}else
			throw new IdNotFound("Work not present on work Id");
	}
	
	public ResponseEntity<ResponseStructure<List<Work>>> fetchAllWork() {
		List<Work>list=dao.fetchAllWork();
		if (list != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<List<Work>>();
			structure.setData(list);
			structure.setMsg("All work fetched");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);

		} else
			throw new IdNotFound("No Work is available now");
		
	}
	public ResponseEntity<ResponseStructure<List<Work>>> fetchAllOnGoingWork() {
		List<Work>list=dao.fetchAllOnGoingWork();
		if (list != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<List<Work>>();
			structure.setData(list);
			structure.setMsg("All work fetched");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);

		} else
			throw new IdNotFound("No Work is available now");
		
	}
	public ResponseEntity<ResponseStructure<List<Work>>> fetchAllCompletedWork() {
		List<Work>list=dao.fetchAllCompletedWork();
		if (list != null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<List<Work>>();
			structure.setData(list);
			structure.setMsg("All work fetched");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure, HttpStatus.FOUND);

		} else
			throw new IdNotFound("No Work is completed now");
		
	}
	public ResponseEntity<ResponseStructure<Work>> deleteWorkById(int id) {
		Work work = dao.findWorkById(id);
		if(work != null) {
			work.setCustomer(null);
			dao.deleteWorkById(id);
			ResponseStructure<Work> structure = new ResponseStructure<Work>();
			structure.setData(work);
			structure.setMsg("Data Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.OK);

		} else
			throw new IdNotFound("No Work is found");
		
		}
	

}
