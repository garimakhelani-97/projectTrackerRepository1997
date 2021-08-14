package com.smrc.mdm.model;

import java.util.List;

public class ProjectProcessAssociationMapper {

	private List<Process> processList;

	public ProjectProcessAssociationMapper(List<Process> processList) {
		super();
		this.processList = processList;
	}

	public List<Process> getProcessList() {
		return processList;
	}

	public void setProcessList(List<Process> processList) {
		this.processList = processList;
	}

	
	
}
