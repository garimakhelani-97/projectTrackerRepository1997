package com.smrc.gpor.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ProjectFinancialResDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ReportAccountsMappingMapper reportAccountsMappingMapper;
	private Integer accountGdtlId;
	private String commitGate;
	private String commitGateValue;
	private String commitGateComment;
	private Integer isFreezed;
	private Integer montGdtlId;
	private String currentValue;
	private String currentComment;
	private String previousValue;
	private String currency;
	

}
