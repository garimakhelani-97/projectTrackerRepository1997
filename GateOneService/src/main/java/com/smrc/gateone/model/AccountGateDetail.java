package com.smrc.gateone.model;

import java.io.Serializable;
import java.util.Set;

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
@Table(name = "account_gate_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountGateDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer projectId;// int 
	private String accountCode;// varchar(245) 
	private String securedProfitability;// varchar(45)
	private String commitGate;// varchar(45)
	private String value;// varchar(45) 
	private String mappingVariable;// int 
	private String comment;// varchar(500) 
	private Integer createdBy;// int 
	private String createdDate;// varchar(45) 
	private Integer updatedBy;// int 
	private String updatedDate;// varchar(45) 
	private String ipAddress;// varchar(45)
	
}
