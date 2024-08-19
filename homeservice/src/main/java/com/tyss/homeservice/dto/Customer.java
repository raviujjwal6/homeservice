package com.tyss.homeservice.dto;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String name;
	@Column(unique = true)
	private String email;
	/*
	 * To store a mobile number as a long type and ensure that it is exactly 10
	 * digits in a Spring Boot application using Spring Data JPA, you need to
	 * perform validation at the service or controller level because annotations
	 * like @Pattern and @Size work with String types, not numeric types like long.
	 * 
	 * @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	 * 
	 * @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
	 * 
	 * @Column(name = "mobile_number", nullable = false, unique = true)
	 */
	private long phone;
	private int familyCount;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Work>works;
	
	

}
