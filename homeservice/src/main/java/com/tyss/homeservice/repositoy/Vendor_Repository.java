package com.tyss.homeservice.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.homeservice.dto.Vendor;

public interface Vendor_Repository extends JpaRepository<Vendor, Integer>{

	Vendor findByEmail(String email);
}
