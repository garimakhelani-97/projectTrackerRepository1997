package com.smrc.mdm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String employeeCode;  
	private String loginId;  
	private String password; 
	private Date effectiveDate; 
	private Date termDate;
	private Date disableDate;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private Date dateOfBirth;
	private String residentialAddress;
	private String officeAddress;
	private String permanentAddress;
	private String phoneNumber;
	private String mobileNumber;
	private String fax;
	private Byte photograph;
	private Integer prSiteId;
	private Integer prDeptId;
	private Integer prDesigId;
	private Integer adminBossId;
	private Integer technicalBossId;
	private String encFlag;
	private String secretQuestion;
	private String secretAnswer;
	private Double statusId; 
	private String ipAddress;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	private String globalUser;
	private String windowUserId;
	private String domainName;
	private Integer globalAccessLevel;
	private Integer globalLocationId;
	private String ssoEnable;
	private Integer segmentId;
	private Integer businessUnitId;
	private Integer locationId;
	private Byte objectSid;
	private String displayName;
	private String moduleAccess;
	private String extensionNumber;
	private String theme;
	private String rcvMailFlag;
	private String distinguishedName;
	private String bloodGroup;
	private String secondaryDomainName;
	private String level;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getTermDate() {
		return termDate;
	}
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}
	public Date getDisableDate() {
		return disableDate;
	}
	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getResidentialAddress() {
		return residentialAddress;
	}
	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Byte getPhotograph() {
		return photograph;
	}
	public void setPhotograph(Byte photograph) {
		this.photograph = photograph;
	}
	public Integer getPrSiteId() {
		return prSiteId;
	}
	public void setPrSiteId(Integer prSiteId) {
		this.prSiteId = prSiteId;
	}
	public Integer getPrDeptId() {
		return prDeptId;
	}
	public void setPrDeptId(Integer prDeptId) {
		this.prDeptId = prDeptId;
	}
	public Integer getPrDesigId() {
		return prDesigId;
	}
	public void setPrDesigId(Integer prDesigId) {
		this.prDesigId = prDesigId;
	}
	public Integer getAdminBossId() {
		return adminBossId;
	}
	public void setAdminBossId(Integer adminBossId) {
		this.adminBossId = adminBossId;
	}
	public Integer getTechnicalBossId() {
		return technicalBossId;
	}
	public void setTechnicalBossId(Integer technicalBossId) {
		this.technicalBossId = technicalBossId;
	}
	public String getEncFlag() {
		return encFlag;
	}
	public void setEncFlag(String encFlag) {
		this.encFlag = encFlag;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	public String getSecretAnswer() {
		return secretAnswer;
	}
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	public Double getStatusId() {
		return statusId;
	}
	public void setStatusId(Double statusId) {
		this.statusId = statusId;
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
	public String getGlobalUser() {
		return globalUser;
	}
	public void setGlobalUser(String globalUser) {
		this.globalUser = globalUser;
	}
	public String getWindowUserId() {
		return windowUserId;
	}
	public void setWindowUserId(String windowUserId) {
		this.windowUserId = windowUserId;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public Integer getGlobalAccessLevel() {
		return globalAccessLevel;
	}
	public void setGlobalAccessLevel(Integer globalAccessLevel) {
		this.globalAccessLevel = globalAccessLevel;
	}
	public Integer getGlobalLocationId() {
		return globalLocationId;
	}
	public void setGlobalLocationId(Integer globalLocationId) {
		this.globalLocationId = globalLocationId;
	}
	public String getSsoEnable() {
		return ssoEnable;
	}
	public void setSsoEnable(String ssoEnable) {
		this.ssoEnable = ssoEnable;
	}
	public Integer getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(Integer segmentId) {
		this.segmentId = segmentId;
	}
	public Integer getBusinessUnitId() {
		return businessUnitId;
	}
	public void setBusinessUnitId(Integer businessUnitId) {
		this.businessUnitId = businessUnitId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Byte getObjectSid() {
		return objectSid;
	}
	public void setObjectSid(Byte objectSid) {
		this.objectSid = objectSid;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getModuleAccess() {
		return moduleAccess;
	}
	public void setModuleAccess(String moduleAccess) {
		this.moduleAccess = moduleAccess;
	}
	public String getExtensionNumber() {
		return extensionNumber;
	}
	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getRcvMailFlag() {
		return rcvMailFlag;
	}
	public void setRcvMailFlag(String rcvMailFlag) {
		this.rcvMailFlag = rcvMailFlag;
	}
	public String getDistinguishedName() {
		return distinguishedName;
	}
	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getSecondaryDomainName() {
		return secondaryDomainName;
	}
	public void setSecondaryDomainName(String secondaryDomainName) {
		this.secondaryDomainName = secondaryDomainName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

}
