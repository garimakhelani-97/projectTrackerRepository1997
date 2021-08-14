package com.smrc.gpor.model;

import java.util.Date;

public class ProcessWiseProject {
	private Integer id;
	private String plantName;
	private String projectId;
	private String projectName;
	private String sopDate;
	private Date statusDate;
	private Integer gateStatusId;
	private String workflow;
	private String customerName;
	private Integer life;
	private String unitDescription;
	private String gate;
	private String overallStatus;
	private String customerGroup;
	private Integer currencyId;
	private String currencyName;

	public ProcessWiseProject() {

	}
		public ProcessWiseProject(Integer id, String plantName, String projectId, String projectName, String sopDate,
			Date statusDate, Integer gateStatusId, String workflow, String customerName, Integer life, String gate,
			String overallStatus, String customerGroup, Integer currencyId, String currencyName) {
		super();
		this.id = id;
		this.plantName = plantName;
		this.projectId = projectId;
		this.projectName = projectName;
		this.sopDate = sopDate;
		this.statusDate = statusDate;
		this.gateStatusId = gateStatusId;
		this.workflow = workflow;
		this.customerName = customerName;
		this.life = life;
		this.gate = gate;
		this.overallStatus = overallStatus;
		this.customerGroup = customerGroup;
		this.currencyId = currencyId;
		this.currencyName = currencyName;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
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

	public String getSopDate() {
		return sopDate;
	}

	public void setSopDate(String sopDate) {
		this.sopDate = sopDate;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Integer getGateStatusId() {
		return gateStatusId;
	}

	public void setGateStatusId(Integer gateStatusId) {
		this.gateStatusId = gateStatusId;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public String getUnitDescription() {
		return unitDescription;
	}
	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}
	
	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public String getOverallStatus() {
		return overallStatus;
	}

	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}

	public String getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	
}
