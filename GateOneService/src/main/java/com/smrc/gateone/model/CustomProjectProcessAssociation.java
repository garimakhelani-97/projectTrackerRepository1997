package com.smrc.gateone.model;

import java.io.Serializable;

public class CustomProjectProcessAssociation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String startDate;
	private String endDate;
	
	public CustomProjectProcessAssociation(Integer id, String name, String startDate, String endDate) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
