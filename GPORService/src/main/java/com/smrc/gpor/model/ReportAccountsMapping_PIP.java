package com.smrc.gpor.model;

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
@Table(name = "report_accounts_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportAccountsMapping_PIP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reportAccountId")
	private Integer reportAccountId;
	private  String reportCategoryId;// varchar(50) 
	private  String misAccountCode;// varchar(50) 
	private  Integer orderId;// int 
	private  Integer isBudgeted;// tinyint 
	private  String shortHeadName;// varchar(400) 
	private  Integer budgetId;// int 
	private  Integer tabLevel;// int 
	private  Integer isBold;// int 
	private  Integer parentId;// int 
	private  Integer mappingVariable;// int 
	private  Integer showInProfitLoss;// tinyint 
	private  Integer showInBalanceSheet;// tinyint 
	private  Integer showInCashFlow;// tinyint 
	private  Integer showInFASchedule;// tinyint 
	private  Integer showInGrossingUp;// tinyint 
	private  String backgroundColor;// varchar(100) 
	private  Integer isPercent;// tinyint 
	private  String rowType;// varchar(45) 
	private  Integer isRowExpandIconEnable;// tinyint 
	private  Integer noOfExpandedRows;// int 
	private  String remarks;// varchar(1000) 
	private  String dbColName;// varchar(50) 
	private  String dbTableName;// varchar(50) 
	private  String conversionType;// varchar(50) 
	private  String conversionDateType;// varchar(50) 
	private  Integer allowBlank;// int 
	private  String accountType;// varchar(30) 
	private  String displayAccountCode;// varchar(50) 
	private  String sourceSystem;// varchar(50) 
	private  Integer statusId;// int 
	private  Integer cUserId;// int 
	private  String cDate;// varchar(45) 
	private  String cIPAddress;// varchar(45) 
	private  Integer uUserId;// int 
	private  String uDate;// varchar(45) 
	private  String uIPAddress;// varchar(45)
}
