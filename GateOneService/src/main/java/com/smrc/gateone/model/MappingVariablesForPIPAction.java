package com.smrc.gateone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MappingVariablesForPIPAction {

	private String mappingVariable;
	private String shortHeadName;
	private String misAccountCode;
	private String securedProfitability;
	private String commitGate;
	private String endofproject;
	private String PIP;
	private String endofprojectVSGate1;
	private String comment;
	private Integer id;
}
