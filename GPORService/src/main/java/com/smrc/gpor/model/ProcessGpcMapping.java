package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="process_gpc_mapping")
public class ProcessGpcMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  private Integer  processId;
	  private Integer userId;
	  private String userName;
	  private String userEmail;
	  private Integer sstatusId;
	  private String ipAddress;
	  private Integer createdBy;
	  private String createdDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getSstatusId() {
		return sstatusId;
	}
	public void setSstatusId(Integer sstatusId) {
		this.sstatusId = sstatusId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	  
	  

}
