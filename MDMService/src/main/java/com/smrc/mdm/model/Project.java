package com.smrc.mdm.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String projectId;
	private String name;
	private Integer parentProjectId;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "brandId",foreignKey = @ForeignKey(name ="FK_brand"),referencedColumnName = "id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "customerGroupId",foreignKey = @ForeignKey(name ="FK_customerGroup"),referencedColumnName = "id")
	private CustomerGroup customerGroup;
	
	@ManyToOne
	@JoinColumn(name = "customerId",foreignKey = @ForeignKey(name = "FK_customer"),referencedColumnName = "id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name ="unitId",foreignKey = @ForeignKey(name="FK_unit"),referencedColumnName = "id")
	private Unit unit;
	
	@OneToOne
	@JoinColumn(name="currencyId",foreignKey = @ForeignKey(name="FK_currency"),referencedColumnName = "id")
	private Currency currency;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "project")
	private List<ProjectProcessAssociation> projectProcessAssociationList;
	
	
	private String ispSopMonth;
	private Integer ispSopYear;
	private String ispEopMonth;
	private Integer ispEopYear;
	private String sopMonth;
	private Integer sopYear;
	private String projectLife;
	private Integer recordStatusID;
	private String ispAwardDate;
	private String awardDate;
	
	@ManyToOne
	@JoinColumn(name="projectStatusId",foreignKey=@ForeignKey(name="FK_projectStatusId"),referencedColumnName = "id")
	private Status status;
	
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private String ipAddress;
	
	@OneToOne
	@JoinColumn(name="productImageId",foreignKey = @ForeignKey(name="FK_productImageId"),referencedColumnName = "id")
	private FileMetaData productFileMetaData;
	
	@OneToOne
	@JoinColumn(name="vehicleImageId",foreignKey = @ForeignKey(name="FK_vehicleImageId"),referencedColumnName = "id")
	private FileMetaData vehicleFileMetaData;
	

	public Project() {

	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentProjectId() {
		return parentProjectId;
	}

	public void setParentProjectId(Integer parentProjectId) {
		this.parentProjectId = parentProjectId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public CustomerGroup getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(CustomerGroup customerGroup) {
		this.customerGroup = customerGroup;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
   
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<ProjectProcessAssociation> getProjectProcessAssociationList() {
		return projectProcessAssociationList;
	}

	public void setProjectProcessAssociationList(List<ProjectProcessAssociation> projectProcessAssociationList) {
		this.projectProcessAssociationList = projectProcessAssociationList;
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

	public String getIspEopMonth() {
		return ispEopMonth;
	}

	public void setIspEopMonth(String ispEopMonth) {
		this.ispEopMonth = ispEopMonth;
	}

	public Integer getIspEopYear() {
		return ispEopYear;
	}

	public void setIspEopYear(Integer ispEopYear) {
		this.ispEopYear = ispEopYear;
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

	

	public Integer getRecordStatusID() {
		return recordStatusID;
	}

	public void setRecordStatusID(Integer recordStatusID) {
		this.recordStatusID = recordStatusID;
	}

	public String getIspAwardDate() {
		return ispAwardDate;
	}

	public void setIspAwardDate(String ispAwardDate) {
		this.ispAwardDate = ispAwardDate;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public FileMetaData getProductFileMetaData() {
		return productFileMetaData;
	}

	public void setProductFileMetaData(FileMetaData productFileMetaData) {
		this.productFileMetaData = productFileMetaData;
	}

	public FileMetaData getVehicleFileMetaData() {
		return vehicleFileMetaData;
	}

	public void setVehicleFileMetaData(FileMetaData vehicleFileMetaData) {
		this.vehicleFileMetaData = vehicleFileMetaData;
	}

	public String getProjectLife() {
		return projectLife;
	}

	public void setProjectLife(String projectLife) {
		this.projectLife = projectLife;
	}

		
	
}
