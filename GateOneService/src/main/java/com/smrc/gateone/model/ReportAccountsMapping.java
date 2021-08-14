package com.smrc.gateone.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.smrc.gpor.model.ReportAccountsMapping;

import lombok.Data;

@Entity
@Table(name = "report_accounts_mapping")
@Data
public class ReportAccountsMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer reportAccountId;
	private String reportCategoryId;
	private String misAccountCode;
	private Integer orderId;
	private Integer isBudgeted;
	private String shortHeadName;
	private Integer budgetId;
	private Integer tabLevel;
	private Integer isBold;
	private Integer parentId;
	private Integer mappingVariable;
	private Integer showInProfitLoss;
	private Integer showInBalanceSheet;
	private Integer showInCashFlow;
	private Integer showInFASchedule;
	private Integer showInGrossingUp;
	private String backgroundColor;
	private Integer isPercent;
	private String rowType;
	private Integer isRowExpandIconEnable;
	private Integer noOfExpandedRows;
	private String remarks;
	private String dbColName;
	private String dbTableName;
	private String conversionType;
	private String conversionDateType;
	private Integer allowBlank;
	private String accountType;
	private String displayAccountCode;
	private String sourceSystem;
	private Integer statusId;
	private Integer cUserId;
	private String cDate;
	private String cIPAddress;
	private Integer uUserId;
	private String uDate;
	private String uIPAddress;
	private String units;

}
