package com.tyss.homeservice.repositoy;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;

import com.tyss.homeservice.dto.Work;

import jakarta.persistence.TemporalType;

public interface Work_Repository extends JpaRepository<Work, Integer> {
	
	@Query("select w from Work w where type=?1 and (StartDate = ?2 or StartDate is null)")
	List<Work> fetchWorkByType(String wtype, @Temporal(TemporalType.DATE)  Date startDate);
}
