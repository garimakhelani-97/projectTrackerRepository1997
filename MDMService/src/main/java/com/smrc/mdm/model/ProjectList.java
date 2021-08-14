package com.smrc.mdm.model;

import java.io.Serializable;
import java.util.List;

public class ProjectList implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String projectId;
	private String projectName;
	private String sopMonth;
	private Integer sopYear;
	private Integer projectLife;
	private String awardDate;
	private Integer recordStatusID;
	private String customerGroupName;
	private String brandName;
	private String customerName;
	private Status status;
	private List<ProcessWithDate> processwithDateList;
	private String plantName;
	private Integer plantId;
	private String ispSopMonth;
	private Integer ispSopYear;
	private String ispAwardDate;
	private Project parentProject;
	private UserInfo projectController;
	private UserInfo projectManager;

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

	public Integer getPlantId() {
		return plantId;
	}

	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}

	public Integer getProjectLife() {
		return projectLife;
	}

	public void setProjectLife(Integer projectLife) {
		this.projectLife = projectLife;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProcessWithDate> getProcesswithDateList() {
		return processwithDateList;
	}

	public void setProcesswithDateList(List<ProcessWithDate> processwithDateList) {
		this.processwithDateList = processwithDateList;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getIspSopMonth() {
		return ispSopMonth;
	}

	public void setIspSopMonth(String ispSopMonth) {
		this.ispSopMonth = ispSopMonth;
	}

	public Integer getIspSopYear() {
		return ispSopYear;
	}

	public void setIspSopYear(Integer ispSopYear) {
		this.ispSopYear = ispSopYear;
	}
   
	public String getIspAwardDate() {
		return ispAwardDate;
	}

	public void setIspAwardDate(String ispAwardDate) {
		this.ispAwardDate = ispAwardDate;
	}

	public Project getParentProject() {
		return parentProject;
	}

	public void setParentProject(Project parentProject) {
		this.parentProject = parentProject;
	}

	public UserInfo getProjectController() {
		return projectController;
	}

	public void setProjectController(UserInfo projectController) {
		this.projectController = projectController;
	}

	public UserInfo getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(UserInfo projectManager) {
		this.projectManager = projectManager;
	}

}
