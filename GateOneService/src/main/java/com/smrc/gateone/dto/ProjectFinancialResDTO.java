package com.smrc.gateone.dto;

import java.io.Serializable;

//import com.smrc.gpor.model.ProjectFinancialResDTO;
//import com.smrc.gpor.model.ReportAccountsMappingMapper;
import com.smrc.gateone.dto.ReportAccountsMappingMapper;
import lombok.Data;

@Data
public class ProjectFinancialResDTO implements Serializable {

	private static final long serialVersionUID = 1L;

//	private ReportAccountsMappingMapper reportAccountsMappingMapper;
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
//	private String currentValue;
	

}
