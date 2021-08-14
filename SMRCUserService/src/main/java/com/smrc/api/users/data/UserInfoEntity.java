package com.smrc.api.users.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class UserInfoEntity implements Serializable {

	private static final long serialVersionUID = 4477382948861367238L;

	@Id
	@GeneratedValue
	private int id;

	@Column
	String employeeCode;

	@Column
	String loginId;

	@Column
	String windowUserId;
	@Column
	String password;

	@Column
	Timestamp effectiveDate;

	@Column
	Timestamp termDate;

	@Column
	Timestamp disableDate;

	@Column
	String firstName;

	@Column
	String lastName;

	@Column
	String email;

	@Column
	char gender;

	@Column
	Timestamp dateOfBirth;

	@Column
	String residentialAddress;

	@Column
	String officeAddress;

	@Column
	String permanentAddress;

	@Column
	String phoneNumber;

	@Column
	String mobileNumber;

	@Column
	String fax;

	@Column
	Byte photograph;

	@Column
	int prSiteId;

	@Column
	int prDeptId;

	@Column
	int prDesigId;

	@Column
	int adminBossId;

	@Column
	int technicalBossId;

	@Column
	char encFlag;

	@Column
	String secretQuestion;

	@Column
	String secretAnswer;

	@Column
	Double statusId;

	@Column
	String ipAddress;

	@Column
	int createdBy;

	@Column
	Timestamp createdDate;

	@Column
	int updatedBy;

	@Column
	Timestamp updatedDate;

	@Column
	char globalUser;

	@Column
	String domainName;

	@Column
	int globalAccessLevel;

	@Column
	int globalLocationId;

	@Column
	char ssoEnable;

	@Column
	int segmentId;

	@Column
	int businessUnitId;

	@Column
	int locationId;

	@Column
	Byte objectSid;

	@Column
	String displayName;

	@Column
	String moduleAccess;

	@Column
	String extensionNumber;

	@Column
	String theme;

	@Column
	String rcvMailFlag;

	@Column
	String distinguishedName;

	@Column
	String bloodGroup;

	@Column
	String secondaryDomainName;

	@Column
	String level;

	@ManyToMany
	@JoinTable(name = "userroleunitassociation", joinColumns = {
			@JoinColumn(name = "userId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "roleId", referencedColumnName = "id") })
	Set<RoleEntity> userRoles;

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

	public Timestamp getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Timestamp getTermDate() {
		return termDate;
	}

	public void setTermDate(Timestamp termDate) {
		this.termDate = termDate;
	}

	public Timestamp getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Timestamp disableDate) {
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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
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

	public int getPrSiteId() {
		return prSiteId;
	}

	public void setPrSiteId(int prSiteId) {
		this.prSiteId = prSiteId;
	}

	public int getPrDeptId() {
		return prDeptId;
	}

	public void setPrDeptId(int prDeptId) {
		this.prDeptId = prDeptId;
	}

	public int getPrDesigId() {
		return prDesigId;
	}

	public void setPrDesigId(int prDesigId) {
		this.prDesigId = prDesigId;
	}

	public int getAdminBossId() {
		return adminBossId;
	}

	public void setAdminBossId(int adminBossId) {
		this.adminBossId = adminBossId;
	}

	public int getTechnicalBossId() {
		return technicalBossId;
	}

	public void setTechnicalBossId(int technicalBossId) {
		this.technicalBossId = technicalBossId;
	}

	public char getEncFlag() {
		return encFlag;
	}

	public void setEncFlag(char encFlag) {
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public char getGlobalUser() {
		return globalUser;
	}

	public void setGlobalUser(char globalUser) {
		this.globalUser = globalUser;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public int getGlobalAccessLevel() {
		return globalAccessLevel;
	}

	public void setGlobalAccessLevel(int globalAccessLevel) {
		this.globalAccessLevel = globalAccessLevel;
	}

	public int getGlobalLocationId() {
		return globalLocationId;
	}

	public void setGlobalLocationId(int globalLocationId) {
		this.globalLocationId = globalLocationId;
	}

	public char getSsoEnable() {
		return ssoEnable;
	}

	public void setSsoEnable(char ssoEnable) {
		this.ssoEnable = ssoEnable;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public int getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
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

	public Set<RoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<RoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public String getWindowUserId() {
		return windowUserId;
	}

	public void setWindowUserId(String windowUserId) {
		this.windowUserId = windowUserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
