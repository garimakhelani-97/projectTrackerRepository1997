package com.smrc.gateone.model;

import java.io.Serializable;
import java.util.List;

import com.smrc.gateone.dto.ProcessAssignMapper;

public class ProjectProcessAssociationModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<UserRoleAssociation> projectControllerList;
	private List<UserRoleAssociation> projectManagerList;
	private List<UserRoleAssociation> programManagerList;
	private List<Process> processList;
	
	public List<UserRoleAssociation> getProjectControllerList() {
		return projectControllerList;
	}
	public void setProjectControllerList(List<UserRoleAssociation> projectControllerList) {
		this.projectControllerList = projectControllerList;
	}
	public List<UserRoleAssociation> getProjectManagerList() {
		return projectManagerList;
	}
	public void setProjectManagerList(List<UserRoleAssociation> projectManagerList) {
		this.projectManagerList = projectManagerList;
	}
	public List<Process> getProcessList() {
		return processList;
	}
	public void setProcessList(List<Process> processList) {
		this.processList = processList;
	}
	public List<UserRoleAssociation> getProgramManagerList() {
		return programManagerList;
	}
	public void setProgramManagerList(List<UserRoleAssociation> programManagerList) {
		this.programManagerList = programManagerList;
	}
	
}
