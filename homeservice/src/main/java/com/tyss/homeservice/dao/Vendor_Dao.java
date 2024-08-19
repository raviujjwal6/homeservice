package com.tyss.homeservice.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.homeservice.dto.Vendor;
import com.tyss.homeservice.repositoy.Vendor_Repository;

@Repository
public class Vendor_Dao {
	@Autowired
	private Vendor_Repository vendor_Repository;

	public Vendor saveVendor(Vendor vendor) {
		return vendor_Repository.save(vendor);
	}

	public Vendor findVendorById(int id) {
		Optional<Vendor> vendor = vendor_Repository.findById(id);
		if (vendor.isPresent()) {
			return vendor.get();
		} else
			return null;
	}

	public Vendor updateVendorDetails(Vendor vendor) {
		return vendor_Repository.save(vendor);
	}

	public void deleteVendorById(int id) {
		vendor_Repository.deleteById(id);
	}
	
	public Vendor login(String email) {
		 Vendor vendor=vendor_Repository.findByEmail(email);
		 if(vendor != null) {
			 return vendor;
		 }else
			 return null;
	}
}
