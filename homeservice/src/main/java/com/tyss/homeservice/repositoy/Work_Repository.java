package com.tyss.homeservice.repositoy;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;

import com.tyss.homeservice.dto.Work;

import jakarta.persistence.TemporalType;

public interface Work_Repository extends JpaRepository<Work, Integer> {
	
	@Query("select w from Work w where type=?1 and (startDate = ?2 or startDate is null)")
	List<Work> fetchWorkByType(String wtype, @Temporal(TemporalType.DATE)  Date startDate);
	
	
	@Query("select w from Work w where (startDate = ?1 or startDate is null) and (endDate = ?2 or endDate is null)")
	List<Work> fetchAllWork(@Temporal(TemporalType.DATE)  Date startDate,@Temporal(TemporalType.DATE)  Date endDate);

	@Query("select w from Work w where w.startDate is not null and (w.endDate = ?1 or w.endDate is null)")
	List<Work> fetchAllOnGoingWork(@Temporal(TemporalType.DATE) Date endDate);

	@Query("select w from Work w where w.startDate is not null and  w.endDate is not null")
	List<Work> fetchAllCompletedWork();

	
	
	
}
