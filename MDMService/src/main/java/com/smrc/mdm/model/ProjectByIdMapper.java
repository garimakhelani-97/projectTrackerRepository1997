package com.smrc.mdm.model;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

public class ProjectByIdMapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Project project;
	private Project parentProject;
	private List<ProcessWithDate> processWithDateList;
    private UserInfo projectManager;
    private UserInfo projectController;
    private List<Unit> technicalCenterList;
    private String convertedProjectLife;
    
   
	private URI productImage;
    private URI vehicleImage;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Project getParentProject() {
		return parentProject;
	}
	public void setParentProject(Project parentProject) {
		this.parentProject = parentProject;
	}
	public List<ProcessWithDate> getProcessWithDateList() {
		return processWithDateList;
	}
	public void setProcessWithDateList(List<ProcessWithDate> processWithDateList) {
		this.processWithDateList = processWithDateList;
	}
	
	public UserInfo getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(UserInfo projectManager) {
		this.projectManager = projectManager;
	}
	public UserInfo getProjectController() {
		return projectController;
	}
	public void setProjectController(UserInfo projectController) {
		this.projectController = projectController;
	}
	public List<Unit> getTechnicalCenterList() {
		return technicalCenterList;
	}
	public void setTechnicalCenterList(List<Unit> technicalCenterList) {
		this.technicalCenterList = technicalCenterList;
	}
	public URI getProductImage() {
		return productImage;
	}
	public void setProductImage(URI productImage) {
		this.productImage = productImage;
	}
	public URI getVehicleImage() {
		return vehicleImage;
	}
	public void setVehicleImage(URI vehicleImage) {
		this.vehicleImage = vehicleImage;
	}
	 public String getConvertedProjectLife() {
			return convertedProjectLife;
		}
		public void setConvertedProjectLife(String convertedProjectLife) {
			this.convertedProjectLife = convertedProjectLife;
		}
	
}
