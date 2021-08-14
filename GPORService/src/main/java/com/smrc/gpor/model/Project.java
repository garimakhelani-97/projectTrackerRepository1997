package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id")
	private Integer id;
	
	@Column(name = "projectRefId",nullable = false)
	private String projectRefId;
	
	@Column(unique = true, name = "mdmId")
	private Integer mdmId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "parentProjectId")
	private Integer parentProjectId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "customerGroupId")
	private Integer customerGroupId;
	
	@Column(name = "customerGroupName")
	private String customerGroupName;
	
	@Column(name = "brandId")
	private Integer brandId;
	
	@Column(name = "brandName")
	private String brandName;
	
	@Column(name = "customerId")
	private Integer customerId;
	
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "unitId")
	private Integer unitId;
	
	@Column(name = "unitName")
	private String unitName;
	
	@Column(name="unitDescription")
	private String unitDescription;
	
	@Column(name = "ispSopMonth")
	private String ispSopMonth;
	
	@Column(name = "ispSopYear")
	private Integer ispSopYear;
	
	@Column(name = "ispEopMonth")
	private String ispEopMonth;
	
	@Column(name = "ispEopYear")
	private Integer ispEopYear;
	
	@Column(name = "sopMonth")
	private String sopMonth;
	
	@Column(name = "sopYear")
	private Integer sopYear;
	
	@Column(name = "projectLife")
	private String projectLife;
	
	@Column(name = "recordStatusID")
	private Integer recordStatusID;
	
	@Column(name = "ispAwardDate")
	private String ispAwardDate;
	
	@Column(name = "awardDate")
	private String awardDate;
	
	@Column(name = "projectStatusId")
	private Integer projectStatusId;
	
	@Column(name = "createdBy")
	private Integer createdBy;
	
	@Column(name = "createdDate")
	private String createdDate;
	
	@Column(name = "updatedBy")
	private Integer updatedBy;
	
	@Column(name = "updatedDate")
	private String updatedDate;
	
	@Column(name = "ipAddress")
	private String ipAddress;
	
	@Column(name = "currenyId")
	private Integer currenyId;
	
	@Column(name = "currencyName")
	private String currencyName;
	
	
	@Column(name = "productImageId")
	private Integer productImageId;
	
	@Column(name = "productImageBlobName")
	private String productImageBlobName;
	
	@Column(name = "vehicleImageId")
	private Integer vehicleImageId;
	
	@Column(name = "vehicleImageBlobName")
	private String vehicleImageBlobName;

	@Column(name = "uploadedDate")
	private String uploadedDate;


}
