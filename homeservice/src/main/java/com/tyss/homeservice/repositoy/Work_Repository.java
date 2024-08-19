package com.tyss.homeservice.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.homeservice.dto.Work;

public interface Work_Repository extends JpaRepository<Work, Integer> {

}
