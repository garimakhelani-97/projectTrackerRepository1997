package com.smrc.mdm.model;

import java.io.Serializable;
import java.util.List;

public class ProjectInputData implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Unit> plantList;
	private List<Unit> technicalCentreList;
	private List<Project> projectList;
	private List<CustomerGroup> customerGroupList;
	private List<Brand> brandList;
	private List<Status> statusList;
	private List<Currency> currencyList;

	public List<Unit> getPlantList() {
		return plantList;
	}

	public void setPlantList(List<Unit> plantList) {
		this.plantList = plantList;
	}

	public List<Unit> getTechnicalCentreList() {
		return technicalCentreList;
	}

	public void setTechnicalCentreList(List<Unit> technicalCentreList) {
		this.technicalCentreList = technicalCentreList;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<CustomerGroup> getCustomerGroupList() {
		return customerGroupList;
	}

	public void setCustomerGroupList(List<CustomerGroup> customerGroupList) {
		this.customerGroupList = customerGroupList;
	}

	public List<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}
}
