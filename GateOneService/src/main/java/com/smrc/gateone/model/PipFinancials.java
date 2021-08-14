package com.smrc.gateone.model;

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
@Table(name = "pip_financials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PipFinancials implements Serializable{


	private static final long serialVersionUID = -6056953861512828351L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer projectId;// int 
	@Column(name = "MAPPING_VARIABLE")
	private String mappingVariable;// int 
	private String comment;// varchar(500) 
	private Integer createdBy;// int 
	private String createdDate;// varchar(45) 
	private Integer updatedBy;// int 
	private String updatedDate;// varchar(45) 
	private String ipAddress;// varchar(45)
	
}
