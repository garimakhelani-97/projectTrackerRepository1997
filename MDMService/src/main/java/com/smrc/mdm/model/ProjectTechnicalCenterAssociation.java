package com.smrc.mdm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="project_technicalcenter_association")
public class ProjectTechnicalCenterAssociation implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private Integer projectId; 
	
	@ManyToOne
	@JoinColumn(name = "unitId", foreignKey = @ForeignKey(name = "FK_unitId_project_tech"), referencedColumnName = "id")
	private Unit unit; 
	private Integer recordStatusId; 
	private Integer createdBy; 
	private String createdDate; 
	private Integer updatedBy; 
	private String updatedDate; 
	private String ipAddress;

}
