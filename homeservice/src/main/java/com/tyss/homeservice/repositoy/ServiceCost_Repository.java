package com.tyss.homeservice.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.homeservice.dto.ServiceCost;

public interface ServiceCost_Repository extends JpaRepository<ServiceCost, Integer> {

}
