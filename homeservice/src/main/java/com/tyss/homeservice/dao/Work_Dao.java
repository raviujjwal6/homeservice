package com.tyss.homeservice.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.homeservice.dto.Work;
import com.tyss.homeservice.repositoy.Work_Repository;

@Repository
public class Work_Dao {
	@Autowired
	private Work_Repository work_Repository;

	public Work saveWork(Work work) {
		return work_Repository.save(work);
	}

	public List<Work> fetchWorkByType(String wtype) {

		Date startDate = null;
		List<Work> list = work_Repository.fetchWorkByType(wtype, startDate);
		if (list.isEmpty())
			return null;
		else
			return list;

	}

	public Work findWorkById(int wid) {
		Optional<Work> work = work_Repository.findById(wid);
		if (work.isPresent())
			return work.get();
		else
			return null;
	}

	// problem with this code still need to work
	public Work updateWork(int wid, Work work) {
		Work work1 = findWorkById(wid);
		if (work1 != null) {
			if (work.getAddress() == null) {
				work.setAddress(work1.getAddress());
			}
			if (work.getStartDate() == null) {
				work.setStartDate(work1.getStartDate());
			}
			if (work.getEndDate() == null) {
				work.setEndDate(work1.getEndDate());
			}
			if (work.getCost() == null) {
				work.setCost(work1.getCost());
			}
			if (work.getCustomer() == null) {
				work.setCustomer(work1.getCustomer());
			}
			if (work.getType() == null) {
				work.setType(work1.getType());
			}
			return work_Repository.save(work);
		} else
			return null;

	}

}
