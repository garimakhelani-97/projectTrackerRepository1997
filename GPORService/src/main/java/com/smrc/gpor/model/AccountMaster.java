package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "account_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountMaster implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "accountId")
	private Integer accountId;

	@Column(name = "sheetId")
	private Integer sheetId;

	@Column(name = "misAccountCode")
	private Double misAccountCode;

	@Column(name = "headName")
	private String headName;

	@Column(name = "formula")
	private String formula;

	@Column(name = "parentId")
	private Integer parentId;

	@Column(name = "hierarchy")
	private String hierarchy;

	@Column(name = "hierarchyLevel")
	private Integer hierarchyLevel;

	@Column(name = "orderId")
	private Integer orderId;

	@Column(name = "tableName")
	private String tableName;

	@Column(name = "ipAddress")
	private String ipAddress;

	@Column(name = "cUserId")
	private Integer cUserId;

	@Column(name = "cDate")
	private String cDate;

	@Column(name = "uUserId")
	private Integer uUserId;

	@Column(name = "uDate")
	private String uDate;

	@Column(name = "assumptionMappingId")
	private Integer assumptionMappingId;

	@Column(name = "metaDataId")
	private Integer metaDataId;

	@Column(name = "mappingVariable",unique = true)
	private Integer mappingVariable;

	@Column(name = "statusId")
	private Integer statusId;

	@Column(name = "currentHidden")
	private String currentHidden;

	@Column(name = "forecastHidden")
	private String forecastHidden;

	@Column(name = "currentIsPercent")
	private String currentIsPercent;

	@Column(name = "forecastIsPercent")
	private String forecastIsPercent;

	@Column(name = "valueDenominator")
	private Integer valueDenominator;

	@Column(name = "editableFlag")
	private String editableFlag;

	@Column(name = "plMappingId")
	private Integer plMappingId;

	@Column(name = "type")
	private String type;

	@Column(name = "percentFormula")
	private String percentFormula;

	@Column(name = "exchangeRateApplicable")
	private String exchangeRateApplicable;

	@Column(name = "showInProfitLoss")
	private String showInProfitLoss;

	@Column(name = "showInBalanceSheet")
	private String showInBalanceSheet;

	@Column(name = "showInCashFlow")
	private String showInCashFlow;

	@Column(name = "showInFASchedule")
	private String showInFASchedule;

	@Column(name = "showInGrossingUp")
	private String showInGrossingUp;

	@Column(name = "isMinus")
	private String isMinus;

	@Column(name = "yearCalcMethod")
	private String yearCalcMethod;

	@Column(name = "grossCode")
	private String grossCode;

	@Column(name = "depType")
	private String depType;

	@Column(name = "bmsMisCode")
	private String bmsMisCode;

	@Column(name = "aggregationMethod")
	private String aggregationMethod;

	@Column(name = "accTypeGroup")
	private String accTypeGroup;

	@Column(name = "dataSource")
	private String dataSource;

	@Column(name = "cashFlowCode")
	private String cashFlowCode;

	@Column(name = "consoleRollUp")
	private String consoleRollUp;

	@Column(name = "nfCalcMethod")
	private String nfCalcMethod;

	@Column(name = "percentCode")
	private Double percentCode;

	@Column(name = "factor")
	private Integer factor;

	@Column(name = "budgetId")
	private Integer budgetId;

	@Column(name = "codeFor")
	private String codeFor;

	@Column(name = "smrShowInBo")
	private String smrShowInBo;

	@Column(name = "smrDay7Code")
	private String smrDay7Code;

	@Column(name = "showInCYEstimate")
	private String showInCYEstimate;

	@Column(name = "hideInCYEstimate")
	private String hideInCYEstimate;


}
