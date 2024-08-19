package com.tyss.homeservice.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.homeservice.dto.Address;

public interface Address_Repository extends JpaRepository<Address, Integer>{

}
