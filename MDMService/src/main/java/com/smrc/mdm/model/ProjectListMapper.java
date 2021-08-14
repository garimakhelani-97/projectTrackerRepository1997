package com.smrc.mdm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProjectListMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String projectId;
	private String projectName;
	private String sopMonth;
	private Integer sopYear;
	private  String projectLife;
	private String awardDate;
	private Integer recordStatusID;
	private String customerGroupName;
	private String customerName;
	private String unitName;
	private String brandName;
	private String status;
	private String updatedDate;
	
	private transient List<String> technicalCenterNameList;

	public ProjectListMapper(Integer id, String projectId, String projectName, String sopMonth, Integer sopYear,
			String projectLife, String awardDate, Integer recordStatusID, String customerGroupName,
			String customerName,String unitName,String brandName, String status, String updatedDate) {//
		super();
		this.id = id;
		this.projectId = projectId;
		this.projectName = projectName;
		this.sopMonth = sopMonth;
		this.sopYear = sopYear;
		this.projectLife = projectLife;
		this.awardDate = awardDate;
		this.recordStatusID = recordStatusID;
		this.customerGroupName  =  customerGroupName;
		this.customerName = customerName;
		this.unitName = unitName;
		this.brandName = brandName;
		this.status = status;
		this.updatedDate = updatedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSopMonth() {
		return sopMonth;
	}

	public void setSopMonth(String sopMonth) {
		this.sopMonth = sopMonth;
	}

	public Integer getSopYear() {
		return sopYear;
	}

	public void setSopYear(Integer sopYear) {
		this.sopYear = sopYear;
	}

	

	public String getProjectLife() {
		return projectLife;
	}

	public void setProjectLife(String projectLife) {
		this.projectLife = projectLife;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}

	public Integer getRecordStatusID() {
		return recordStatusID;
	}

	public void setRecordStatusID(Integer recordStatusID) {
		this.recordStatusID = recordStatusID;
	}

	public String getCustomerGroupName() {
		return customerGroupName;
	}

	public void setCustomerGroupName(String customerGroupName) {
		this.customerGroupName = customerGroupName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<String> getTechnicalCenterNameList() {
		return technicalCenterNameList;
	}

	public void setTechnicalCenterNameList(List<String> technicalCenterNameList) {
		this.technicalCenterNameList = technicalCenterNameList;
	}
	
	
	

}
