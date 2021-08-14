package com.smrc.gpor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="project_gate_detail")
public class ProjectGateDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer projectId;
	private Integer gateId;
	private Date initialDate;
	private Date effectiveDate;
	private Integer percentageLRR;
	private Integer statusId;
	private String reportedMonth;
	private Date createdOn;
	private Date updatedOn;
	private Integer createdBy;
	private Integer updatedBy; 
	private String ipAddress;
	
	private Integer isFreezed;

	public ProjectGateDetail() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getGateId() {
		return gateId;
	}

	public void setGateId(Integer gateId) {
		this.gateId = gateId;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getPercentageLRR() {
		return percentageLRR;
	}

	public void setPercentageLRR(Integer percentageLRR) {
		this.percentageLRR = percentageLRR;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getReportedMonth() {
		return reportedMonth;
	}

	public void setReportedMonth(String reportedMonth) {
		this.reportedMonth = reportedMonth;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	

	public Integer getIsFreezed() {
		return isFreezed;
	}

	public void setIsFreezed(Integer isFreezed) {
		this.isFreezed = isFreezed;
	}

	

}
