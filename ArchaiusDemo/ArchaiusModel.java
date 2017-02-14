package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class ArchaiusModel {
	
	private String name;
	private String id;
	private String company;
	public ArchaiusModel(){
		//default constructor
	}
	
	
	public ArchaiusModel(String name, String id, String company) {
		super();
		this.name = name;
		this.id = id;
		this.company = company;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}
	
	

}
