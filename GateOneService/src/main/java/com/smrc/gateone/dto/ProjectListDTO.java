package com.smrc.gateone.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectListDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String projectRefId;;
	private String name;
	private Integer unitId;
	private String unitName;
	private String unitDescription;
	private String updatedDate;
	private Integer currenyId;
	private String currencyName;
	private String sopMonth;
	private Integer sopYear;
	private transient List<String> assignProcessNameList;
	private Integer isFreezed;
	private Integer recordStatusID;
	
	private transient List<String> processNameList;
	private List<String> startDate;
	private List<String> endDate;
	
	public ProjectListDTO(Integer id, String projectRefId, String name, Integer unitId, String unitName,
			String unitDescription, String updatedDate, Integer currenyId, String currencyName, String sopMonth,
			Integer sopYear,Integer recordStatusID) {
		super();
		this.id = id;
		this.projectRefId = projectRefId;
		this.name = name;
		this.unitId = unitId;
		this.unitName = unitName;
		this.unitDescription = unitDescription;
		this.updatedDate = updatedDate;
		this.currenyId = currenyId;
		this.currencyName = currencyName;
		this.sopMonth = sopMonth;
		this.sopYear = sopYear;
		this.recordStatusID = recordStatusID;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectRefId() {
		return projectRefId;
	}

	public void setProjectRefId(String projectRefId) {
		this.projectRefId = projectRefId;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitDescription() {
		return unitDescription;
	}

	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getCurrenyId() {
		return currenyId;
	}

	public void setCurrenyId(Integer currenyId) {
		this.currenyId = currenyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
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

	public List<String> getAssignProcessNameList() {
		return assignProcessNameList;
	}

	public void setAssignProcessNameList(List<String> assignProcessNameList) {
		this.assignProcessNameList = assignProcessNameList;
	}

	public Integer getIsFreezed() {
		return isFreezed;
	}

	public void setIsFreezed(Integer isFreezed) {
		this.isFreezed = isFreezed;
	}

	public Integer getRecordStatusID() {
		return recordStatusID;
	}

	public void setRecordStatusID(Integer recordStatusID) {
		this.recordStatusID = recordStatusID;
	}

	public List<String> getProcessNameList() {
		return processNameList;
	}

	public void setProcessNameList(List<String> processNameList) {
		this.processNameList = processNameList;
	}

	public List<String> getStartDate() {
		return startDate;
	}

	public void setStartDate(List<String> startDate) {
		this.startDate = startDate;
	}

	public List<String> getEndDate() {
		return endDate;
	}

	public void setEndDate(List<String> endDate) {
		this.endDate = endDate;
	}

}
