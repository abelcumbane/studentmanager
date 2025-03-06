package com.mapulassapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	@NotEmpty(message = "Name can not be empty")
	private String name; 
	
	@Column
	@Min(value = 0, message = "Age can not be smaller than 0")
	@Max(value = 120, message = "Age can not be larger than 120") 
	@NotNull(message = "Age can not be empty")
	private int age;
	
	@Column
	@Min(value = 4, message = "Zip code can not be smaller than 4")
	@Max(value = 9999, message = "Zip code can not be larger than 9999")
	@NotNull(message = "Zip code can not be empty")
	@Digits(integer = 4, fraction =0, message = "Zip code is acepting only 4 digits")
	private int zipCode;
	
	@Column
	@NotEmpty(message = "Country can not be empty")
	private String country;
	
	@ManyToOne
	@JoinColumn
	private Status status;
	
	public Student() {
		
	}
	
	public Student(String name, int age, int zipCode, String country, Status status) {
		this.name = name;
		this.age = age;
		this.zipCode = zipCode;
		this.country = country;
		this.status = status;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}	

}
