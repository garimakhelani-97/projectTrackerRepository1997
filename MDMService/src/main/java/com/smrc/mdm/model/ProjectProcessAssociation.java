package com.smrc.mdm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProjectProcessAssociation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="processId",foreignKey = @ForeignKey(name = "FK_processId"),referencedColumnName = "id")
	private Process process;
	
	@ManyToOne
	@JoinColumn(name = "projectId",foreignKey=@ForeignKey(name="FK_projectId"),referencedColumnName = "id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name ="projectControllerId",foreignKey = @ForeignKey(name="FK_projectControllerId"),referencedColumnName = "id")
	private UserInfo projectController;
	
	@ManyToOne
	@JoinColumn(name = "projectManagerId",foreignKey = @ForeignKey(name = "FK_projectManagerId"),referencedColumnName = "id")
	private UserInfo projectManager;
	
	private String startDate; 
	private String endDate;
	private Integer recordStatusId;
	private String createdBy; 
	private String createdDate; 
	private String updatedBy; 
	private String updatedDate; 
	private String ipAddress; 

	public ProjectProcessAssociation() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public Integer getRecordStatusId() {
		return recordStatusId;
	}

	public void setRecordStatusId(Integer recordStatusId) {
		this.recordStatusId = recordStatusId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
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
