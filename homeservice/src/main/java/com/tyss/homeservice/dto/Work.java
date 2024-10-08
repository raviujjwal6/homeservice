package com.tyss.homeservice.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wid;
	private Date startDate;
	private Date endDate;
	private String type;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Customer customer;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Vendor> vendors;
	@OneToOne(cascade = CascadeType.ALL)
	private ServiceCost cost;
	
	

}
