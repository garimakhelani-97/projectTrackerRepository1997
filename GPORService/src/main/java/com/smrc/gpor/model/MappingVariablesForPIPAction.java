package com.smrc.gpor.model;

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
	private String currentvalue;
	private String gate1commitvalue;
	private String endofproject;
	private String PIP;
	private String endofprojectVSGate1;
	private String comment;
	private Integer id;
}
