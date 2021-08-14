package com.smrc.gpor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class PipFinancials {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "projectId",nullable = false)
	private String projectId;

	@Column(name = "month")
	private String month;

	@Column(name = "year")
	private String year;

	@Column(name = "comment")
	private String comment;

	@Column(name = "cdate")
	private String cdate;

	@Column(name = "MAPPING_VARIABLE")
	private String mappingVariable;
	
//	@OneToOne
//	@JoinColumn(name = "mappingVariable", foreignKey = @ForeignKey(name = "pipfinancial_mapping_variable_fk"), referencedColumnName = "MAPPING_VARIABLE")
//	private AccountMaster accountMaster;

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


}
