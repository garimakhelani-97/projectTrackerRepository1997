package com.smrc.gpor.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportAccountsMappingMapper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String misAccountCode;
	private String shortHeadName;
	private Integer mappingVariable;
	private String units;

}
