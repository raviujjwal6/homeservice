package com.tyss.homeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.homeservice.dao.Vendor_Dao;
import com.tyss.homeservice.dto.Vendor;
import com.tyss.homeservice.exception.IdNotFound;
import com.tyss.homeservice.exception.Login_Exception;
import com.tyss.homeservice.util.EmailUtil;
import com.tyss.homeservice.util.ResponseStructure;

@Service
public class Vendor_Service {
	@Autowired
	private Vendor_Dao dao;
	@Autowired
	private EmailUtil emailUtil;
	
	private ResponseStructure<Vendor> structure = new ResponseStructure<Vendor>();

	public ResponseEntity<ResponseStructure<Vendor>> saveVendor(Vendor vendor) {
		//dao.saveVendor(vendor);
		structure.setData(dao.saveVendor(vendor));
		structure.setData(vendor);
		structure.setMsg("vendor details saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		emailUtil.sendEmailVendor(vendor.getEmail());
		return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> findVendorById(int id) {
		Vendor vendor = dao.findVendorById(id);
		if(vendor != null) {
			structure.setData(vendor);
			structure.setMsg("vendor found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.FOUND);
		}else {
			throw new IdNotFound("vendors id not found");
		}
			
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> updateVendorDetails(Vendor vendor) {
		structure.setData(dao.updateVendorDetails(vendor));
		structure.setMsg("data modified");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> deletevendorById(int id) {
		Vendor vendor = dao.findVendorById(id);
		if(vendor != null) {
			dao.deleteVendorById(id);
			structure.setData(vendor);
			structure.setMsg("vendor data deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.OK);
		}else {
			throw new IdNotFound("vendor id not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Vendor>> login(String email,String pwd) {
		Vendor vendor=dao.login(email);
		if(vendor != null) {
			if(vendor.getEmail().equals(email) && vendor.getPassword().equals(pwd)) {
				structure.setData(vendor);
				structure.setMsg("login sucessfull");
				structure.setStatusCode(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Vendor>>(structure,HttpStatus.FOUND);
			}else {
				throw new Login_Exception("incorrect password");
			}
		}else {
			throw new Login_Exception("incorrect emailId");
		}
	}
	
	
}
