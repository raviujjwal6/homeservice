package com.tyss.homeservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
//vendor is nothing but worker
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vid;
	private String name;
	private String email;
	private String password;
	private long phoneNumber;
	private String role;
	private double costPerday;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Work>works
	/*
	 * one vendors can have many servicecost
	 */;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ServiceCost> serviceCosts;
	

}
