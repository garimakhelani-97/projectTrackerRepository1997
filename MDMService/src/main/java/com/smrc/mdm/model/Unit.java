package com.smrc.mdm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private Integer mindMdmUnitId;  
	private String name;
	private String description;
	private Integer regionId; 
	private Integer countryId; 
	private Integer stateId; 
	private Integer cityId; 
	private Integer regionalOfficeId; 
	private Integer clusterId; 
	private Integer subClusterId; 
	private Integer companyId; 
	private Integer parentId; 
	private String smrcMgmtReportingRegionId; 
	private String hfmOploc; 
	private String isTechnicalCenter; 
	private Integer currencyId;  
	private Integer recordStatusId; 
	private String ipAddress; 
	private Integer createdBy; 
	private Date createdDate; 
	private Integer updatedBy; 
	private Date updatedDate; 
	private Integer consolidateFlag; 
	private Integer isOnlyBs;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMindMdmUnitId() {
		return mindMdmUnitId;
	}
	public void setMindMdmUnitId(Integer mindMdmUnitId) {
		this.mindMdmUnitId = mindMdmUnitId;
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
//	public List<Project> getProjectList() {
//		return projectList;
//	}
//	public void setProjectList(List<Project> projectList) {
//		this.projectList = projectList;
//	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getRegionalOfficeId() {
		return regionalOfficeId;
	}
	public void setRegionalOfficeId(Integer regionalOfficeId) {
		this.regionalOfficeId = regionalOfficeId;
	}
	public Integer getClusterId() {
		return clusterId;
	}
	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}
	public Integer getSubClusterId() {
		return subClusterId;
	}
	public void setSubClusterId(Integer subClusterId) {
		this.subClusterId = subClusterId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getSmrcMgmtReportingRegionId() {
		return smrcMgmtReportingRegionId;
	}
	public void setSmrcMgmtReportingRegionId(String smrcMgmtReportingRegionId) {
		this.smrcMgmtReportingRegionId = smrcMgmtReportingRegionId;
	}
	public String getHfmOploc() {
		return hfmOploc;
	}
	public void setHfmOploc(String hfmOploc) {
		this.hfmOploc = hfmOploc;
	}
	public String getIsTechnicalCenter() {
		return isTechnicalCenter;
	}
	public void setIsTechnicalCenter(String isTechnicalCenter) {
		this.isTechnicalCenter = isTechnicalCenter;
	}
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	public Integer getRecordStatusId() {
		return recordStatusId;
	}
	public void setRecordStatusId(Integer recordStatusId) {
		this.recordStatusId = recordStatusId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getConsolidateFlag() {
		return consolidateFlag;
	}
	public void setConsolidateFlag(Integer consolidateFlag) {
		this.consolidateFlag = consolidateFlag;
	}
	public Integer getIsOnlyBs() {
		return isOnlyBs;
	}
	public void setIsOnlyBs(Integer isOnlyBs) {
		this.isOnlyBs = isOnlyBs;
	} 

	
}
