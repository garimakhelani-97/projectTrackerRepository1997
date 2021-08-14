package com.smrc.gpor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workflow_submission")
public class WorkFlowSubmission implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer unitId;
	private Integer projectId;
	private Integer processId;
	private String reportedMonth;
	private Integer submitBy;
	private Date submitOn;
	private Date dueDte;
	private Integer createdBy;
	private String createdDate;
	private Integer updatedBy;
	private String updatedDate;
	private String ipAddress;

	public WorkFlowSubmission() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public String getReportedMonth() {
		return reportedMonth;
	}

	public void setReportedMonth(String reportedMonth) {
		this.reportedMonth = reportedMonth;
	}

	public Integer getSubmitBy() {
		return submitBy;
	}

	public void setSubmitBy(Integer submitBy) {
		this.submitBy = submitBy;
	}

	public Date getSubmitOn() {
		return submitOn;
	}

	public void setSubmitOn(Date submitOn) {
		this.submitOn = submitOn;
	}

	public Date getDueDte() {
		return dueDte;
	}

	public void setDueDte(Date dueDte) {
		this.dueDte = dueDte;
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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


}
