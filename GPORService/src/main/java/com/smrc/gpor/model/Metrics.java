package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Metrics implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String name;
	private String description;
	private Integer createdBy; 
	private String createdDate; 
	private Integer updatedBy; 
	private String updatedDate; 
	private String ipAddress; 
	
	public Metrics() {
		// TODO Auto-generated constructor stub
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	
//	public Integer getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(Integer createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public String getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(String createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public Integer getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(Integer updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//
//	public String getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(String updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//	public String getIpAddress() {
//		return ipAddress;
//	}
//
//	public void setIpAddress(String ipAddress) {
//		this.ipAddress = ipAddress;
//	}

}
