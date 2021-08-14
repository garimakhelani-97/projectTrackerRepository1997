package com.smrc.mdm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProjectMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private Unit plant;
	private List<Unit> technicalCentre;
	private Project parentProject;
	private CustomerGroup customerGroup;
	private Brand brand;
	private String projectLife;
	private String ispsopDate;
	private Date sopdate;
	private Status projectStatus;
	private String ispAwardDate;
	private Date awardDate;
	private Integer userId;
	private String ipAddress;
	/*
	 * private UserInfo projectController; private UserInfo projectManager; private
	 * List<ProcessWithDate> processWithDateList;
	 */
	private Integer recordStatusId;
	private Currency currency;
	private Integer productImageId;
	private String productImageBlobName;
	private Integer vehicleImageId;
	private String vehicleImageBlobName;
	private String uploadedDate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Unit getPlant() {
		return plant;
	}

	public void setPlant(Unit plant) {
		this.plant = plant;
	}
	
	public List<Unit> getTechnicalCentre() {
		return technicalCentre;
	}

	public void setTechnicalCentre(List<Unit> technicalCentre) {
		this.technicalCentre = technicalCentre;
	}

	public Project getParentProject() {
		return parentProject;
	}

	public void setParentProject(Project parentProject) {
		this.parentProject = parentProject;
	}

	public CustomerGroup getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(CustomerGroup customerGroup) {
		this.customerGroup = customerGroup;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	
	

	

	public String getProjectLife() {
		return projectLife;
	}

	public void setProjectLife(String projectLife) {
		this.projectLife = projectLife;
	}

	public Date getSopdate() {
		return sopdate;
	}

	public void setSopdate(Date sopdate) {
		this.sopdate = sopdate;
	}

	public Status getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Status projectStatus) {
		this.projectStatus = projectStatus;
	}

	
	public Date getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/*
	 * public UserInfo getProjectController() { return projectController; }
	 * 
	 * public void setProjectController(UserInfo projectController) {
	 * this.projectController = projectController; }
	 * 
	 * public UserInfo getProjectManager() { return projectManager; }
	 * 
	 * public void setProjectManager(UserInfo projectManager) { this.projectManager
	 * = projectManager; }
	 * 
	 * public List<ProcessWithDate> getProcessWithDateList() { return
	 * processWithDateList; }
	 * 
	 * public void setProcessWithDateList(List<ProcessWithDate> processWithDateList)
	 * { this.processWithDateList = processWithDateList; }
	 */

	public Integer getRecordStatusId() {
		return recordStatusId;
	}

	public void setRecordStatusId(Integer recordStatusId) {
		this.recordStatusId = recordStatusId;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getIspsopDate() {
		return ispsopDate;
	}

	public void setIspsopDate(String ispsopDate) {
		this.ispsopDate = ispsopDate;
	}

	public String getIspAwardDate() {
		return ispAwardDate;
	}

	public void setIspAwardDate(String ispAwardDate) {
		this.ispAwardDate = ispAwardDate;
	}

	public Integer getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(Integer productImageId) {
		this.productImageId = productImageId;
	}

	public String getProductImageBlobName() {
		return productImageBlobName;
	}

	public void setProductImageBlobName(String productImageBlobName) {
		this.productImageBlobName = productImageBlobName;
	}

	public Integer getVehicleImageId() {
		return vehicleImageId;
	}

	public void setVehicleImageId(Integer vehicleImageId) {
		this.vehicleImageId = vehicleImageId;
	}

	public String getVehicleImageBlobName() {
		return vehicleImageBlobName;
	}

	public void setVehicleImageBlobName(String vehicleImageBlobName) {
		this.vehicleImageBlobName = vehicleImageBlobName;
	}

	public String getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}	
	
}
