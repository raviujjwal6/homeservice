package com.tyss.homeservice.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.homeservice.dao.ServiceCost_Dao;
import com.tyss.homeservice.dao.Vendor_Dao;
import com.tyss.homeservice.dao.Work_Dao;
import com.tyss.homeservice.dto.ServiceCost;
import com.tyss.homeservice.dto.Vendor;
import com.tyss.homeservice.dto.Work;
import com.tyss.homeservice.exception.IdNotFound;
import com.tyss.homeservice.repositoy.ServiceCost_Repository;
import com.tyss.homeservice.util.ResponseStructure;

@Service
public class ServiceCost_Service {
	@Autowired
	private ServiceCost_Repository repository;
	@Autowired
	private Work_Dao  work_Dao;
	@Autowired
	private Vendor_Dao  vendor_Dao;
	
	@Autowired
	private ServiceCost_Repository serviceCost_Repository;
	
	
	ResponseStructure<Work> structure = new ResponseStructure<Work>();
	
	public ResponseEntity<ResponseStructure<Work>> startWork(int wid,int vid) {
		Work work = work_Dao.findWorkById(wid);
		Vendor vendor=vendor_Dao.findVendorById(vid);
		
		List<Vendor>listVendor = new ArrayList<Vendor>();
		listVendor.add(vendor);
		
		if(work != null && vendor != null) {
			work.setStartDate(Date.from(Instant.now()));
			work.setVendors(listVendor);
			
			work_Dao.updateWork(wid, work);
			structure.setData(work);
			structure.setMsg("work mapped");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Work>>(structure,HttpStatus.OK);
		}else {
			throw new IdNotFound("Id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Work>> endWork(int wid,int vid) {
		Work work=work_Dao.findWorkById(wid);
		Vendor vendor=vendor_Dao.findVendorById(vid);
		if(work != null && vendor != null) {
			work.setEndDate(Date.from(Instant.now()));
			
			Work work2 = work_Dao.updateWork(wid, work);
			Date st = work2.getStartDate();
			Date et=work2.getEndDate();
			Duration duration = Duration.between(st.toInstant(),et.toInstant());
			long day = duration.toDays();
			
			
			ServiceCost serviceCost = new ServiceCost();
			serviceCost.setDays((int)day);
//			serviceCost.setMode(List.of("PhonePay,GooglePay,Paytm,Upi"));
			serviceCost.setTotalAmount(vendor.getCostPerday()*day);
			ServiceCost db = repository.save(serviceCost);
			work2.setCost(serviceCost);
			work_Dao.updateWork(wid, work2);
			structure.setData(work2);
			structure.setMsg("work ended");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Work>>(structure,HttpStatus.OK);
		}else {
			throw new IdNotFound("No Work is Present to finished");
		}
	}
	
	public ResponseEntity<ResponseStructure<ServiceCost>> payment(int payment_id,String mode) {
		 Optional<ServiceCost> db = serviceCost_Repository.findById(payment_id);
		
		 ResponseStructure<ServiceCost>structure1=new ResponseStructure<ServiceCost>();
		if(db.isPresent()) {
		   ServiceCost payment = db.get();
			payment.setMode(mode);
			serviceCost_Repository.save(payment);
			structure1.setData(payment);
			structure1.setMsg("payment done successfully");
			structure1.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<ServiceCost>>(structure1,HttpStatus.OK);
		}else {
			throw new IdNotFound("Id not found");
		}
	}
	

}
